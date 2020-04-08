package com.wesleyfuchter.bankaccount.transaction

interface TransactionProducer {

    fun produce(transaction: Transaction): Transaction

}