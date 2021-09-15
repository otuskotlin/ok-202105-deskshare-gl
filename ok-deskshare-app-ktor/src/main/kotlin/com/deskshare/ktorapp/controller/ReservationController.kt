package com.deskshare.ktorapp.controller

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.RequestInterface
import com.deskshare.common.context.command.CreateCommandRequest
import com.deskshare.common.context.command.DeleteCommandRequest
import com.deskshare.common.context.command.UpdateCommandRequest
import com.deskshare.common.context.query.FindByFilterQueryRequest
import com.deskshare.common.context.query.FindByIdQueryRequest
import com.deskshare.common.models.error.CommonError
import com.deskshare.dto.mapping.rest.toErrorDto
import com.deskshare.ktorapp.service.respond
import com.deskshare.service.ReservationServiceInterface
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.request.*

class ReservationController(private val service: ReservationServiceInterface) : ReservationControllerInterface {
    override suspend fun create(call: ApplicationCall) {
        call.handleRequest(request = CreateCommandRequest()) {
            service.create(ctx = this, requestDto = call.receive())
        }
    }

    override suspend fun update(call: ApplicationCall) {
        call.handleRequest(request = UpdateCommandRequest()) {
            service.update(ctx = this, requestDto = call.receive())
        }
    }

    override suspend fun delete(call: ApplicationCall) {
        call.handleRequest(request = DeleteCommandRequest()) {
            service.delete(ctx = this, reservationId = call.parameters["id"].toString())
        }
    }

    override suspend fun findById(call: ApplicationCall) {
        call.handleRequest(request = FindByIdQueryRequest()) {
            service.findById(ctx = this, reservationId = call.parameters["id"].toString())
        }
    }

    override suspend fun findAll(call: ApplicationCall) {
        // todo implement filter, paging etc.
        call.handleRequest(request = FindByFilterQueryRequest()) {
            service.findByFilter(ctx = this)
        }
    }
}

private suspend inline fun <U : Any, R : RequestInterface> ApplicationCall.handleRequest(
    request: R,
    block: RequestContext<R>.() -> U
) {
    // todo read stub from http header
    val ctx = RequestContext(
        requestId = callId.toString(),
        request = request,
        stubCase = true
    )

    try {
        respond(ctx, ctx.block())
    } catch (e: Throwable) {
        ctx.finishedWithError(CommonError.fromThrowable(e))
        respond(ctx, ctx.toErrorDto())
    }
}
