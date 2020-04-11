package com.wesleyfuchter.bankaccount.balance

import com.mongodb.client.MongoClient
import io.quarkus.mongodb.panache.PanacheMongoEntity
import io.quarkus.mongodb.panache.PanacheMongoRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped class BalancesImpl(
        private val client: MongoClient
): Balances, PanacheMongoRepository<Balance> {

    override fun saveOrUpdate(balance: Balance): Balance =
            balance.also {
                persistOrUpdate(balance)
            }

    override fun findByAccountId(accountId: String): Balance? =
            find("accountId", accountId).firstResult()

    override fun updateBalance(transaction: Transaction): Balance =
            transaction.let {

                val currentBalance =
                        findByAccountId(transaction.accountId) ?:
                        Balance.defaultInstance(transaction.accountId)

                val balanceValue =
                        if (transaction.type == TransactionType.INCOME)
                            currentBalance.value + transaction.value
                        else
                            currentBalance.value - transaction.value

                val balance = currentBalance.copy(accountId = transaction.accountId, value = balanceValue)

                saveOrUpdate(balance)

            }
}