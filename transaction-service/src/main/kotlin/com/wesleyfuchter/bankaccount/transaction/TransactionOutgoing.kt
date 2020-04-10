package com.wesleyfuchter.bankaccount.transaction

import io.reactivex.Flowable
import io.smallrye.reactive.messaging.kafka.KafkaRecord
import org.eclipse.microprofile.reactive.messaging.Outgoing
import java.util.*
import java.util.concurrent.TimeUnit
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class TransactionOutgoing {

    @Outgoing("transactions")
    fun producer(): Flowable<KafkaRecord<String, Transaction>> =
            Flowable.interval(1000, TimeUnit.MILLISECONDS)
                    .onBackpressureDrop()
                    .map {
                        println("something is happening here!")
                        KafkaRecord.of(UUID.randomUUID().toString(),
                                Transaction(
                                        id = UUID.randomUUID().toString(),
                                        accountId = "wesley1",
                                        description = "test",
                                        value = 100.0,
                                        type = TransactionType.INCOME
                                )
                        )
                    }

}

