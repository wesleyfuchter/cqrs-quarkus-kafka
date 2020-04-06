package com.wesleyfuchter.bankaccount.balance

import java.time.LocalDateTime

data class Balance (
        val _id: String? = null,
        val accountId: String,
        val value: Double,
        val lastChangedDate: LocalDateTime
)