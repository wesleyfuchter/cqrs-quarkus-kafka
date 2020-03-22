package com.wesleyfuchter.bankaccount.transaction

import javax.enterprise.context.ApplicationScoped

@ApplicationScoped interface Transactions {

    fun create(transaction: Transaction): Transaction

}