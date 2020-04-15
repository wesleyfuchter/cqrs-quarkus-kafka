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

@ApplicationScoped
class TransactionIncomeProducer(
        private val balances: Balances
) {

    private val log = Logger.getLogger("TransactionIncomeProducer")

    @Produces
    fun onTransactionTopology(): Topology {
        val builder = StreamsBuilder()
        builder.stream("transactions",
                Consumed.with(Serdes.String(),
                        Serdes.serdeFrom(JsonObjectSerializer(), JsonObjectDeserializer())))
                .foreach { _, value: JsonObject ->
                    val transaction = Transaction.ofMap(value.map)
                    log.info("Receiving transaction with description ${transaction.description}")
                    balances.recalculate(transaction)
                }
        return builder.build()
    }

}