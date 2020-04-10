package com.wesleyfuchter.bankaccount.transaction

interface TransactionEmitter {

    fun send(transaction: Transaction)

}