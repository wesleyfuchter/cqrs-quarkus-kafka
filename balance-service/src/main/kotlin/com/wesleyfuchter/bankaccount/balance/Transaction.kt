package com.wesleyfuchter.bankaccount.balance

class Transaction(
        val id: String = "",
        val accountId: String,
        val description: String,
        val type: TransactionType,
        val value: Double
)

enum class TransactionType {
    INCOME, EXPENSE
}