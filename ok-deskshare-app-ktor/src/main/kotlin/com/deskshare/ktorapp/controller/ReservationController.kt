package com.deskshare.ktorapp.controller

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.RequestInterface
import com.deskshare.common.context.command.CreateCommandRequest
import com.deskshare.common.context.command.DeleteCommandRequest
import com.deskshare.common.context.command.UpdateCommandRequest
import com.deskshare.common.context.query.FindByFilterQueryRequest
import com.deskshare.common.context.query.FindByIdQueryRequest
import com.deskshare.ktorapp.service.respond
import com.deskshare.service.ReservationServiceInterface
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.request.*

class ReservationController(private val service: ReservationServiceInterface) : ReservationControllerInterface {
    // @todo read from header
    private val stubCase: Boolean = true

    override suspend fun create(call: ApplicationCall) {
        val commandCtx = RequestContext(
            requestId = call.callId.toString(),
            request = CreateCommandRequest(),
            stubCase = stubCase
        )

        call.handleRequest(ctx = commandCtx, service = service)
    }

    override suspend fun update(call: ApplicationCall) {
        val commandCtx = RequestContext(
            requestId = call.callId.toString(),
            request = UpdateCommandRequest(),
            stubCase = stubCase
        )

        call.handleRequest(ctx = commandCtx, service = service)
    }

    override suspend fun delete(call: ApplicationCall) {
        val commandCtx = RequestContext(
            requestId = call.callId.toString(),
            request = DeleteCommandRequest(),
            stubCase = stubCase
        )

        call.handleRequest(ctx = commandCtx, service = service)
    }

    override suspend fun findById(call: ApplicationCall) {
        val queryCtx = RequestContext(
            requestId = call.callId.toString(),
            request = FindByIdQueryRequest(),
            stubCase = stubCase
        )

        call.handleRequest(ctx = queryCtx, service = service)
    }

    override suspend fun findAll(call: ApplicationCall) {
        // todo implement filter, paging etc.
        val queryCtx = RequestContext(
            requestId = call.callId.toString(),
            request = FindByFilterQueryRequest(),
            stubCase = stubCase
        )

        call.handleRequest(ctx = queryCtx, service = service)
    }
}

private suspend inline fun <reified T : RequestInterface> ApplicationCall.handleRequest(
    ctx: RequestContext<T>,
    service: ReservationServiceInterface
) {
    respond(
        ctx,
        try {
            when (T::class.java) {
                CreateCommandRequest::class.java -> service.create(ctx as RequestContext<CreateCommandRequest>, receive())
                UpdateCommandRequest::class.java -> service.update(ctx as RequestContext<UpdateCommandRequest>, receive())
                DeleteCommandRequest::class.java -> service.delete(ctx as RequestContext<DeleteCommandRequest>, receive())
                FindByIdQueryRequest::class.java -> service.findById(ctx as RequestContext<FindByIdQueryRequest>, parameters["id"].toString())
                FindByFilterQueryRequest::class.java -> service.findByFilter(ctx as RequestContext<FindByFilterQueryRequest>)
                else -> throw Error("Unknown request type")
            }
        } catch (e: Throwable) {
            service.handleError(ctx, e)
        }
    )
}
