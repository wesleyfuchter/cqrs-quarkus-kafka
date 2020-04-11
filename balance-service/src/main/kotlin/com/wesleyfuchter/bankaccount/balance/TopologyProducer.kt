package com.wesleyfuchter.bankaccount.balance

import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializer
import io.vertx.core.json.JsonObject
import io.vertx.kafka.client.serialization.JsonObjectDeserializer
import io.vertx.kafka.client.serialization.JsonObjectSerializer
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.Topology
import org.apache.kafka.streams.kstream.Consumed
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Produces

@ApplicationScoped class TopologyProducer(
        private val balances: Balances
) {


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

                    println(transaction.accountId)

                    val balance = balances.updateBalance(transaction)

                    println(balance.id)

                }

        return builder.build()
    }

}