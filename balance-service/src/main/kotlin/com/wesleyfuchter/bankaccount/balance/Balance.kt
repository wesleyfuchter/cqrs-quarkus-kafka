package com.wesleyfuchter.bankaccount.balance

import io.quarkus.mongodb.panache.MongoEntity
import org.bson.types.ObjectId

@MongoEntity(collection = "balances")
data class Balance (
        var id: ObjectId? = null,
        var accountId: String,
        var value: Double
) {

    fun recalculate(transaction: Transaction) =
            copy(accountId = transaction.accountId, value =
                if (transaction.type == TransactionType.INCOME)
                    this.value + transaction.value
                else
                    this.value - transaction.value)

    companion object {

        @JvmStatic fun defaultInstance(accountId: String): Balance =
                Balance(
                    accountId = accountId,
                    value = 0.0
                )

    }

}