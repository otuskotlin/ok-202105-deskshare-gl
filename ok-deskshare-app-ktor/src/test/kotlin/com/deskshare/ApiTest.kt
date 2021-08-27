package com.deskshare

import com.deskshare.dto.mapping.rest.toDto
import com.deskshare.openapi.models.CreateReservationDto
import com.deskshare.openapi.models.UpdateReservationDto
import com.deskshare.openapi.models.ViewReservationDto
import com.deskshare.stubs.Reservation
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
    fun `get reservation by id (not found)`() = withApplication(testEnv) {
        with(handleRequest(HttpMethod.Get, "/reservations/123123123")) {
            assertEquals(HttpStatusCode.NotFound, response.status())
        }
    }

    @Test
    fun `get all reservations`() = withApplication(testEnv) {
        with(handleRequest(HttpMethod.Get, "/reservations")) {
            assertEquals(HttpStatusCode.OK, response.status())
        }
    }

    @Test
    fun `new reservation`() = withApplication(testEnv) {
        with(handleRequest(HttpMethod.Post, "/reservations") {
            addHeader(HttpHeaders.ContentType, "application/json")
            setBody(
                om.writeValueAsString(
                    CreateReservationDto(
                        description = "test",
                        userId = "123",
                        workspaceId = "123",
                        from = "2021-01-01T10:10:23",
                        until = "2021-01-01T15:10:23"
                    )
                )
            )
        }) {
            assertEquals(HttpStatusCode.Created, response.status())
            assertEquals(
                om.readValue(response.content, ViewReservationDto::class.java),
                Reservation.getPendingModel().toDto()
            )
        }
    }

    @Test
    fun `update reservation by id`() = withApplication(testEnv) {
        with(handleRequest(HttpMethod.Put, "/reservations/123") {
            addHeader(HttpHeaders.ContentType, "application/json")
            setBody(
                om.writeValueAsString(
                    UpdateReservationDto(
                        description = "test",
                        userId = "123",
                        workspaceId = "123",
                        from = "2021-01-01T10:10:23",
                        until = "2021-01-01T15:10:23",
                        id = "123"
                    )
                )
            )
        }) {
            assertEquals(HttpStatusCode.OK, response.status())
            assertEquals(
                om.readValue(response.content, ViewReservationDto::class.java),
                Reservation.getModel().toDto()
            )
        }
    }

    @Test
    fun `delete reservation by id`() = withApplication(testEnv) {
        with(handleRequest(HttpMethod.Delete, "/reservations/123") {
            addHeader(HttpHeaders.ContentType, "application/json")
        }) {
            assertEquals(HttpStatusCode.OK, response.status())
            assertEquals(
                om.readValue(response.content, ViewReservationDto::class.java),
                Reservation.getCanceledModel().toDto()
            )
        }
    }
}
