package com.deskshare

import com.fasterxml.jackson.databind.ObjectMapper
import com.typesafe.config.ConfigFactory
import io.ktor.config.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Test
import kotlin.test.assertEquals

class ApiValidationTest {
    private val om = ObjectMapper()
    private val testEnv = createTestEnvironment {
        config = HoconApplicationConfig(ConfigFactory.load("application-test.conf"))
    }

    @Test
    fun `new reservation with invalid json body`() {
        withApplication(testEnv) {
            handleRequest(HttpMethod.Post, "/reservations") {
                addHeader(HttpHeaders.ContentType, "application/json")
                setBody("[test")
            }.apply {
                assertEquals(HttpStatusCode.InternalServerError, response.status())
            }
        }
    }
}
