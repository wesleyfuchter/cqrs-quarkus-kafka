package com.wesleyfuchter.bankaccount.transaction

import java.net.URI
import javax.transaction.Transactional
import javax.validation.Valid
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/transactions")
class TransactionResource(
        private val transactions: Transactions,
        private val transactionProducer: TransactionProducer
) {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    fun add(@Valid transaction: Transaction): Response =
            transactions.add(transaction).let {
                transactionProducer.produce(it)
                Response.created(URI("/transactions/${it.id}")).entity(it).build()
            }

}