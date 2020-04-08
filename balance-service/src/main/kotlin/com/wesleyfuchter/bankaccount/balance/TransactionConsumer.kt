package com.wesleyfuchter.bankaccount.balance

import io.smallrye.reactive.messaging.annotations.Broadcast
import org.eclipse.microprofile.reactive.messaging.Incoming

interface TransactionConsumer {

    @Incoming("transactions") @Broadcast fun consume(transaction: Transaction): Transaction

}