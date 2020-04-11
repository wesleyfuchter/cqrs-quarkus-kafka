package com.wesleyfuchter.bankaccount.balance

interface Balances {

    fun saveOrUpdate(balance: Balance): Balance

    fun findByAccountId(accountId: String): Balance?

    fun updateBalance(transaction: Transaction): Balance

}