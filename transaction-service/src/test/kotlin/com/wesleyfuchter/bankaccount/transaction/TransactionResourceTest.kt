package com.wesleyfuchter.bankaccount.transaction

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.junit.jupiter.api.Test
import io.restassured.http.ContentType
import org.hamcrest.CoreMatchers.notNullValue
import javax.json.bind.JsonbBuilder

@QuarkusTest
class TransactionResourceTest {

    @Test
    fun testSaveUser() {
        given()
            .contentType(ContentType.JSON)
            .body(JsonbBuilder.create().toJson(mapOf(
                    "description" to "salary",
                    "accountId" to "test$123",
                    "type" to "INCOME",
                    "value" to 100.0))
            )
            .`when`().post("/transactions")
            .then()
            .statusCode(201)
            .body("id", notNullValue())
    }

}