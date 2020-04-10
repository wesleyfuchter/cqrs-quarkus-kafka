package com.wesleyfuchter.bankaccount.balance

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import javax.enterprise.context.ApplicationScoped
import com.mongodb.client.model.Filters.eq
import java.time.LocalDateTime
import java.util.UUID

@ApplicationScoped class BalancesImpl(
        private val client: MongoClient
): Balances {

    override fun add(balance: Balance): Balance =
            balance.copy(_id = UUID.randomUUID().toString()).also {
                collection().insertOne(it)
            }

    override fun set(balance: Balance): Balance =
            balance.also {
                collection().replaceOne(eq("_id", balance._id), it)
            }

    override fun findByAccountId(accountId: String): Balance? =
            collection().find(eq("accountId", accountId)).first()

    private fun collection(): MongoCollection<Balance> = client.getDatabase("bankaccount")
            .getCollection("balances", Balance::class.java)


    override fun updateBalance(transaction: Transaction): Balance =
            transaction.let {

                val currentBalance = findByAccountId(transaction.accountId) ?: Balance(
                        accountId = transaction.accountId,
                        value = 0.0)

                val balanceValue =
                        if (transaction.type == TransactionType.INCOME)
                            currentBalance.value + transaction.value
                        else
                            currentBalance.value - transaction.value

                val balance = currentBalance.copy(accountId = transaction.accountId, value = balanceValue)

                if (balance._id == null) {

                    add(balance)

                } else {

                    set(balance)

                }

            }
}