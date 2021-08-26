package com.deskshare.ktorapp.service

import com.deskshare.common.models.error.CommonError
import com.deskshare.ktorapp.isDev
import com.deskshare.ktorapp.isProd
import com.deskshare.ktorapp.isTest
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.response.*
import org.koin.ktor.ext.Koin

fun Application.init() {
    install(StatusPages) {
        when {
            isProd -> exception<Throwable> {
                call.respond(
                    status = HttpStatusCode.InternalServerError,
                    message = CommonError.fromRuntime("Internal Server Error"))
            }
            isDev || isTest -> exception<Throwable> { e ->
                call.respond(
                    status = HttpStatusCode.InternalServerError,
                    message = CommonError.fromRuntime(e.stackTraceToString()))
            }
        }
    }

    install(CallId) {
        retrieveFromHeader(HttpHeaders.XRequestId)
        replyToHeader(HttpHeaders.XRequestId)
    }

    install(Compression) {
        gzip()
    }

    install(ContentNegotiation) {
        jackson {
            disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            enable(SerializationFeature.INDENT_OUTPUT)
            writerWithDefaultPrettyPrinter()
        }
    }

    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.Authorization)
        anyHost()
    }

    install(Koin) {
        modules(deskshare)
    }
}
