package com.wesleyfuchter.bankaccount.transaction

data class Transaction(
        val id: String,
        val accountId: String,
        val description: String,
        val transactionType: TransactionType,
        val value: Double
)

enum class TransactionType {
    INCOME, EXPENSE
}