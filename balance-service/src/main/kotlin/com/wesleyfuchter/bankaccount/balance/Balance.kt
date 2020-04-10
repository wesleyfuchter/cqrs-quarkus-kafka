package com.wesleyfuchter.bankaccount.balance

@MongoEntity
data class Balance (
        val _id: String? = null,
        val accountId: String,
        val value: Double
)