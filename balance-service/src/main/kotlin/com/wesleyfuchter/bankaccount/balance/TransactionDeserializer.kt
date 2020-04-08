package com.wesleyfuchter.bankaccount.balance

import io.quarkus.kafka.client.serialization.JsonbDeserializer

class TransactionDeserializer : JsonbDeserializer<Transaction>(Transaction::class.java)