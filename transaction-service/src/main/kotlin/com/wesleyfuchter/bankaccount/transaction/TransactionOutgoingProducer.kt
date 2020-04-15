package com.wesleyfuchter.bankaccount.transaction

import io.smallrye.reactive.messaging.kafka.KafkaRecord
import org.eclipse.microprofile.reactive.messaging.Incoming
import org.eclipse.microprofile.reactive.messaging.Outgoing
import java.util.*
import java.util.logging.Logger
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class TransactionOutgoingProducer {

    private val log = Logger.getLogger("TransactionOutgoingProducer")

    @Incoming("transactions")
    @Outgoing("transactions")
    fun produce(transaction: Transaction): KafkaRecord<String, Transaction> =
            KafkaRecord.of(UUID.randomUUID().toString(), transaction).also {
                log.info("Sending transaction with description ${transaction.description}")
            }

}

