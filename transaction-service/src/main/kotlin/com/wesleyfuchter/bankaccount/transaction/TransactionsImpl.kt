package com.wesleyfuchter.bankaccount.transaction

import javax.enterprise.context.ApplicationScoped
import javax.persistence.EntityManager

@ApplicationScoped class TransactionsImpl(
        private val database: EntityManager
): Transactions {

    override fun add(transaction: Transaction): Transaction =
            transaction.also {
                database.persist(transaction)
            }

}