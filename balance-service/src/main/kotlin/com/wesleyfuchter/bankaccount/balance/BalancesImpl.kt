package com.wesleyfuchter.bankaccount.balance

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import javax.enterprise.context.ApplicationScoped
import com.mongodb.client.model.Filters.eq
import java.util.UUID

@ApplicationScoped class BalancesImpl(
        private val client: MongoClient
): Balances {

    override fun add(balance: Balance): Balance =
            balance.copy(_id = UUID.randomUUID().toString()).also {
                collection().insertOne(it)
            }

    override fun set(balance: Balance): Balance =
            balance.also {
                collection().replaceOne(eq("_id", balance._id), it)
            }

    override fun findByAccountId(accountId: String): Balance? =
            collection().find(eq("accountId", accountId)).first()

    private fun collection(): MongoCollection<Balance> = client.getDatabase("bankaccount")
            .getCollection("balances", Balance::class.java)

}