package com.wesleyfuchter.bankaccount.transaction

import io.agroal.api.AgroalDataSource
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped class TransactionsImpl(
        private val database: AgroalDataSource
): Transactions {

    override fun add(transaction: Transaction): Transaction =
            database.connection.prepareStatement(
                    """
                        INSERT INTO transactions 
                               (accountId, description, type, value) 
                        VALUES (:accountId, :description, :type, :value) 
                        RETURNING (id)
                    """).executeQuery().let { resultSet -> transaction.copy(id = resultSet.getString("id"))}

}