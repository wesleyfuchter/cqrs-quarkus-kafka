package com.wesleyfuchter.bankaccount.balance

interface Balances {

    fun add(balance: Balance): Balance

    fun set(balance: Balance): Balance

    fun findByAccountId(accountId: String): Balance?

    fun updateBalance(transaction: Transaction): Balance

}