package com.wesleyfuchter.bankaccount.transaction

import java.net.URI
import javax.transaction.Transactional
import javax.validation.Valid
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

import org.eclipse.microprofile.reactive.messaging.Channel
import org.eclipse.microprofile.reactive.messaging.Emitter

@Path("/transactions")
class TransactionResource(
        @Channel("transactions") private val emitter: Emitter<Transaction>
) {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    fun add(@Valid transaction: Transaction): Response =
            transaction.persist().let {
                emitter.send(transaction)
                Response.created(URI("/transactions/${transaction.id}")).entity(transaction).build()
            }

}