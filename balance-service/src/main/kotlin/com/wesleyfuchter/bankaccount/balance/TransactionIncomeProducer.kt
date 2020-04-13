package com.wesleyfuchter.bankaccount.balance

import io.vertx.core.json.JsonObject
import io.vertx.kafka.client.serialization.JsonObjectDeserializer
import io.vertx.kafka.client.serialization.JsonObjectSerializer
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.Topology
import org.apache.kafka.streams.kstream.Consumed
import java.util.logging.Logger
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Produces

@ApplicationScoped class TransactionIncomeProducer(
        private val balances: Balances
) {

    private val log = Logger.getLogger("TransactionIncomeProducer")

    @Produces fun buildTopology(): Topology {
        val builder = StreamsBuilder()
        builder.stream("transactions", Consumed.with(Serdes.String(),
                Serdes.serdeFrom(JsonObjectSerializer(), JsonObjectDeserializer())))
                .foreach { _, value: JsonObject ->
                    val transactionAsMap = value.map
                    val transaction = Transaction(
                            id = transactionAsMap["id"] as String,
                            accountId = transactionAsMap["accountId"] as String,
                            description = transactionAsMap["description"] as String,
                            value = transactionAsMap["value"] as Double,
                            type = TransactionType.valueOf(transactionAsMap["type"] as String)
                    )
                    log.info("Receiving transaction with description ${transaction.description}")
                    balances.updateBalance(transaction)
                }
        return builder.build()
    }

}