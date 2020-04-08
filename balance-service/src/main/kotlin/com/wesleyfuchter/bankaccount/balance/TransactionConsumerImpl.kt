package com.wesleyfuchter.bankaccount.balance

import javax.enterprise.context.ApplicationScoped

@ApplicationScoped class TransactionConsumerImpl: TransactionConsumer {

    override fun consume(transaction: Transaction): Transaction =
            transaction.also { println(transaction) }

}