package com.wesleyfuchter.bankaccount.balance

import io.quarkus.runtime.annotations.RegisterForReflection

@RegisterForReflection
class Transaction(
        val id: String = "",
        val accountId: String,
        val description: String,
        val type: TransactionType,
        val value: Double
) {

    companion object {

        @JvmStatic fun ofMap(transactionAsMap: Map<String, Any>) =
                Transaction(
                    id = transactionAsMap["id"] as String,
                    accountId = transactionAsMap["accountId"] as String,
                    description = transactionAsMap["description"] as String,
                    value = transactionAsMap["value"] as Double,
                    type = TransactionType.valueOf(transactionAsMap["type"] as String)
                )

    }

}

enum class TransactionType {
    INCOME, EXPENSE
}