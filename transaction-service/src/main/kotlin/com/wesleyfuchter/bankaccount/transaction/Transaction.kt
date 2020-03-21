package com.wesleyfuchter.bankaccount.transaction

data class Transaction(
        val tenantId: String,
        val transactionId: String,
        val description: String,
        val transactionType: TransactionType,
        val value: Double
)

enum class TransactionType {
    INCOME, EXPENSE
}