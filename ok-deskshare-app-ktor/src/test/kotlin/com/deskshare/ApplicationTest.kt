package com.deskshare

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun `health endpoint`() {
        withTestApplication(Application::module) {
            handleRequest(HttpMethod.Get, "/health").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("I am healthy", response.content)
            }
        }
    }

    @Test
    fun `get reservation by id`() {
        withTestApplication(Application::module) {
            handleRequest(HttpMethod.Get, "/reservations/1").apply {
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    @Test
    fun `get all reservation`() {
        withTestApplication(Application::module) {
            handleRequest(HttpMethod.Get, "/reservations").apply {
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    @Test
    fun `create a new reservation`() {
        withTestApplication(Application::module) {
            handleRequest(HttpMethod.Post, "/reservations").apply {
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    @Test
    fun `delete reservation by id`() {
        withTestApplication(Application::module) {
            handleRequest(HttpMethod.Delete, "/reservations/1").apply {
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    @Test
    fun `edit reservation by id`() {
        withTestApplication(Application::module) {
            handleRequest(HttpMethod.Put, "/reservations/1").apply {
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }
}
