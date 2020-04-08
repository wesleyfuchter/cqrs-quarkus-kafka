package com.wesleyfuchter.bankaccount.transaction

import org.eclipse.microprofile.reactive.messaging.Channel
import org.eclipse.microprofile.reactive.messaging.Emitter
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped class TransactionProducerImpl(
        @Channel("transactions") private val emitter: Emitter<Transaction>
) : TransactionProducer {

    override fun produce(transaction: Transaction): Transaction =
            transaction.also {
                emitter.send(transaction)
            }

}