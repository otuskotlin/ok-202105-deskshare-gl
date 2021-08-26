package com.deskshare.ktorapp.controller

import com.deskshare.common.context.CommandRequestContext
import com.deskshare.common.context.QueryRequestContext
import com.deskshare.common.models.error.CommonError
import com.deskshare.dto.mapping.rest.toDto
import com.deskshare.dto.mapping.rest.toErrorDtoIfHas
import com.deskshare.dto.mapping.rest.toModel
import com.deskshare.service.ReservationCommandServiceInterface
import com.deskshare.service.ReservationQueryServiceInterface
import com.deskshare.ktorapp.service.toHttpStatusCode
import com.deskshare.openapi.models.CreateReservationDto
import com.deskshare.openapi.models.UpdateReservationDto
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*

class ReservationController(
    private val commandService: ReservationCommandServiceInterface,
    private val queryService: ReservationQueryServiceInterface
) : ReservationControllerInterface {
    override suspend fun create(call: ApplicationCall) {
        val commandCtx = CommandRequestContext.forCreateReservation(
            newModel = call.receive<CreateReservationDto>().toModel(),
            requestId = call.callId.toString()
        )
        try {
            commandService.create(commandCtx).apply {
                call.respond(
                    status = HttpStatusCode.Created,
                    message = toDto()
                )
            }
        } catch (e: Throwable) {
            with(commandCtx) {
                finishedWithError(CommonError.fromThrowable(e))
                call.respond(
                    status = toHttpStatusCode(),
                    message = toErrorDtoIfHas()
                )
            }
        }
    }

    override suspend fun update(call: ApplicationCall) {
        val commandCtx = CommandRequestContext.forUpdateReservation(
            oldModel = call.receive<UpdateReservationDto>().toModel(),
            requestId = call.callId.toString()
        )
        try {
            commandService.update(commandCtx).apply {
                call.respond(toDto())
            }
        } catch (e: Throwable) {
            with(commandCtx) {
                finishedWithError(CommonError.fromThrowable(e))
                call.respond(
                    status = toHttpStatusCode(),
                    message = toErrorDtoIfHas()
                )
            }
        }
    }

    override suspend fun delete(call: ApplicationCall) {
        val commandCtx = CommandRequestContext.forDeleteReservation(
            id = call.parameters["id"].toString(),
            requestId = call.callId.toString()
        )
        try {
            commandService.delete(commandCtx).apply {
                call.respond(toDto())
            }
        } catch (e: Throwable) {
            with(commandCtx) {
                finishedWithError(CommonError.fromThrowable(e))
                call.respond(
                    status = toHttpStatusCode(),
                    message = toErrorDtoIfHas()
                )
            }
        }
    }

    override suspend fun findById(call: ApplicationCall) {
        val queryCtx = QueryRequestContext.forFindById(
            id = call.parameters["id"].toString(),
            requestId = call.callId.toString()
        )

        try {
            queryService.findById(queryCtx).apply {
                call.respond( toDto() )
            }
        } catch (e: Throwable) {
            with(queryCtx) {
                finishedWithError(com.deskshare.common.models.error.CommonError.fromThrowable(e))
                call.respond(
                    status = toHttpStatusCode(),
                    message = toErrorDtoIfHas()
                )
            }
        }
    }

    override suspend fun findAll(call: ApplicationCall) {
        // todo implement filter, paging etc.
        val queryCtx = QueryRequestContext.forFindByFilter(call.callId.toString())

        try {
            queryService.findByFilter(queryCtx).apply {
                call.respond(map { it.toDto() })
            }
        } catch (e: Throwable) {
            with(queryCtx) {
                finishedWithError(com.deskshare.common.models.error.CommonError.fromThrowable(e))
                call.respond(
                    status = toHttpStatusCode(),
                    message = toErrorDtoIfHas()
                )
            }
        }
    }
}
