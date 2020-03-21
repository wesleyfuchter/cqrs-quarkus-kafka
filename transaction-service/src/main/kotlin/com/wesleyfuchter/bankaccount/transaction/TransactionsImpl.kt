package com.wesleyfuchter.bankaccount.transaction

import javax.enterprise.context.ApplicationScoped

@ApplicationScoped class TransactionsImpl: Transactions {

    override fun hello(): String = "hello from service"

}