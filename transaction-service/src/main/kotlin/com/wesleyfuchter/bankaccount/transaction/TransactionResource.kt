package com.wesleyfuchter.bankaccount.transaction

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/transaction")
class TransactionResource(
        private val transactions: Transactions
) {

}