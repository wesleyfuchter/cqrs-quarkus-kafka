package com.wesleyfuchter.bankaccount.transaction

import org.eclipse.microprofile.reactive.messaging.Channel
import org.eclipse.microprofile.reactive.messaging.Emitter
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped class TransactionEmitterImpl(
        @Channel("transactions") private val emitter: Emitter<Transaction>
) : TransactionEmitter {

    override fun send(transaction: Transaction) {
        emitter.send(transaction)
    }

}