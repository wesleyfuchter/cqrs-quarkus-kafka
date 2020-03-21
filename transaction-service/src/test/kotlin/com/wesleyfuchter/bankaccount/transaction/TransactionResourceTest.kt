package com.wesleyfuchter.bankaccount.transaction

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
open class TransactionResourceTest {

    @Test
    fun testHelloEndpoint() {
        given()
          .`when`().get("/transaction")
          .then()
             .statusCode(200)
             .body(`is`("hello from service"))
    }

}