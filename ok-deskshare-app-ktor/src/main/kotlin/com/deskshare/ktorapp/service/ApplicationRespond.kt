package com.deskshare.ktorapp.service

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.RequestInterface
import com.deskshare.common.context.command.CreateCommandRequest
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*

suspend fun <T : RequestInterface> ApplicationCall.respond(ctx: RequestContext<T>, responseDto: Any) {
    respond(
        status = getHttpStatusCode(ctx),
        message = responseDto
    )
}

private fun <T : RequestInterface> getHttpStatusCode(ctx: RequestContext<T>): HttpStatusCode =
    // @todo other http codes (Auth, Validation error or not found)
    if (ctx.isSuccess()) {
        when (ctx.request) {
            is CreateCommandRequest -> HttpStatusCode.Created
            else -> HttpStatusCode.OK
        }
    } else {
        HttpStatusCode.InternalServerError
    }
