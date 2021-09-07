package com.deskshare

import com.deskshare.dto.mapping.rest.toDto
import com.deskshare.openapi.models.CreateReservationDto
import com.deskshare.openapi.models.UpdateReservationDto
import com.deskshare.openapi.models.ViewReservationDto
import com.deskshare.stubs.ReservationStub
import com.fasterxml.jackson.databind.ObjectMapper
import com.typesafe.config.ConfigFactory
import io.ktor.config.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

class ApiTest {
    private val om = ObjectMapper()
    private val testEnv = createTestEnvironment {
        config = HoconApplicationConfig(ConfigFactory.load("application-test.conf"))
    }

    private inline fun <reified T> testCommand(method: HttpMethod, uri: String, message: Any?, crossinline block: T.(call: TestApplicationCall) -> Unit) = withApplication(testEnv) {
        handleRequest(method, uri) {
            addHeader(HttpHeaders.ContentType, "application/json")
            message?.let { setBody(om.writeValueAsString(message)) }
        }.apply {
            om.readValue(response.content, T::class.java).block(this)
        }
    }

    @Test
    fun `health endpoint`() = withApplication(testEnv) {
        with(handleRequest(HttpMethod.Get, "/health")) {
            assertEquals(HttpStatusCode.OK, response.status())
            assertEquals("I am healthy :)", response.content)
        }
    }

    @Test
    fun `request id`() = withApplication(testEnv) {
        val requestId = UUID.randomUUID().toString()
        with(handleRequest(HttpMethod.Get, "/heath") {
            addHeader(HttpHeaders.XRequestId, requestId)
        }) {
            assertEquals(requestId, response.headers.get(HttpHeaders.XRequestId))
        }
    }

    @Test
    fun `get reservation by id`() = withApplication(testEnv) {
        with(handleRequest(HttpMethod.Get, "/reservations/123")) {
            assertEquals(HttpStatusCode.OK, response.status())
        }
    }

    @Test
    fun `get all reservations`() = withApplication(testEnv) {
        with(handleRequest(HttpMethod.Get, "/reservations")) {
            assertEquals(HttpStatusCode.OK, response.status())
        }
    }

    @Test
    fun `new reservation`() {
        val dto = CreateReservationDto(
            description = "test",
            userId = "123",
            workspaceId = "123",
            from = "2021-01-01T10:10:23",
            until = "2021-01-01T15:10:23"
        )

        testCommand<ViewReservationDto>(
            method = HttpMethod.Post,
            uri = "/reservations",
            message = dto) { call: TestApplicationCall ->
                assertEquals(HttpStatusCode.Created, call.response.status())
                assertEquals(
                    this,
                    ReservationStub.getPendingModel().toDto()
                )
        }
    }

    @Test
    fun `update reservation by id`() {
        val dto = UpdateReservationDto(
            description = "test",
            userId = "123",
            workspaceId = "123",
            from = "2021-01-01T10:10:23",
            until = "2021-01-01T15:10:23",
            id = "123"
        )

        testCommand<ViewReservationDto>(
            method = HttpMethod.Put,
            uri = "/reservations/123",
            message = dto) { call: TestApplicationCall ->
                assertEquals(HttpStatusCode.OK, call.response.status())
                assertEquals(this, ReservationStub.getModel().toDto()
            )
        }
    }

    @Test
    fun `delete reservation by id`() {
        testCommand<ViewReservationDto>(
            method = HttpMethod.Delete,
            uri = "/reservations/123",
            message = null) { call: TestApplicationCall ->
                assertEquals(HttpStatusCode.OK, call.response.status())
                assertEquals(this, ReservationStub.getCanceledModel().toDto()
            )
        }
    }
}
