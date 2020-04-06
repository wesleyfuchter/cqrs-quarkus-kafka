package com.wesleyfuchter.bankaccount.balance

import javax.transaction.Transactional
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/balance")
class BalanceResource(
        private val balances: Balances
) {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    fun findByAccountId(@QueryParam("accountId") accountId: String): Response =
            Response.ok(balances.findByAccountId(accountId)).build()

}