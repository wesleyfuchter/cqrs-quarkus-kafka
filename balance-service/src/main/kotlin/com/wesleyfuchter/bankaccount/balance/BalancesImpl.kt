package com.wesleyfuchter.bankaccount.balance

import io.quarkus.mongodb.panache.PanacheMongoRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class BalancesImpl: Balances, PanacheMongoRepository<Balance> {

    override fun saveOrUpdate(balance: Balance): Balance =
            balance.also {
                persistOrUpdate(balance)
            }

    override fun findByAccountId(accountId: String): Balance? =
            find("accountId", accountId).firstResult()

    override fun recalculate(transaction: Transaction): Balance =
            transaction.let {
                val currentBalance =
                        findByAccountId(transaction.accountId) ?:
                        Balance.defaultInstance(transaction.accountId)
                saveOrUpdate(currentBalance.recalculate(transaction))
            }

}