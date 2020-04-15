package com.wesleyfuchter.bankaccount.balance

import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.Test
import javax.inject.Inject

@QuarkusTest
class BalancesTest {

    @Inject lateinit var balances: Balances

    @Test
    fun `it should find an existing balance`() =
            balances.saveOrUpdate(Balance(
                    accountId = "test@123",
                    value = 100.0
            )).let { addedBalance ->
                val balance = balances.findByAccountId(addedBalance.accountId)
                assert(balance?.accountId == addedBalance.accountId)
                assert(balance?.value == 100.0)
            }

}