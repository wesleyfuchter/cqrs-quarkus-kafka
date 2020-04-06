package com.wesleyfuchter.bankaccount.balance

import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import javax.inject.Inject

@QuarkusTest
class BalancesTest {

    @Inject lateinit var balances: Balances

    @Test
    fun `it should add a new balance`() =
            assert(balances.add(Balance(
                    accountId = "test@123",
                    value = 100.0,
                    lastChangedDate = LocalDateTime.now()
            ))._id != null)

    @Test
    fun `it should set a new balance`() =
            balances.add(Balance(
                    accountId = "test@123",
                    value = 100.0,
                    lastChangedDate = LocalDateTime.now()
            )).let { addedBalance ->
                val updatedBalance = balances.set(addedBalance.copy(value = 200.0))
                assert(updatedBalance._id == addedBalance._id)
                assert(updatedBalance.value == 200.0)
            }

    @Test
    fun `it should find an existing balance`() =
            balances.add(Balance(
                    accountId = "test@123",
                    value = 100.0,
                    lastChangedDate = LocalDateTime.now()
            )).let { addedBalance ->
                val balance = balances.findByAccountId(addedBalance.accountId)
                assert(balance?._id == addedBalance._id)
                assert(balance?.value == 100.0)
            }

}