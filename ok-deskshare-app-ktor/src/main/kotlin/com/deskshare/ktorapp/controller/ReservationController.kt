package com.deskshare.ktorapp.controller

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.command.CreateCommandRequest
import com.deskshare.common.context.command.DeleteCommandRequest
import com.deskshare.common.context.command.UpdateCommandRequest
import com.deskshare.common.context.query.FindByFilterQueryRequest
import com.deskshare.common.context.query.FindByIdQueryRequest
import com.deskshare.common.models.ReservationIdModel
import com.deskshare.common.models.error.CommonError
import com.deskshare.dto.mapping.rest.toDto
import com.deskshare.dto.mapping.rest.toErrorDtoIfHas
import com.deskshare.dto.mapping.rest.toModel
import com.deskshare.service.ReservationServiceInterface
import com.deskshare.openapi.models.CreateReservationDto
import com.deskshare.openapi.models.UpdateReservationDto
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*

class ReservationController(private val service: ReservationServiceInterface) : ReservationControllerInterface {
    // @todo read from header
    private val stubCase: Boolean = true

    override suspend fun create(call: ApplicationCall) {
        val commandCtx = RequestContext(
            requestId = call.callId.toString(),
            request = CreateCommandRequest(call.receive<CreateReservationDto>().toModel()),
            stubCase = stubCase
        )
        try {
            service.create(commandCtx)
            call.respond(
                status = HttpStatusCode.Created,
                message = commandCtx.request.responseModel.toDto()
            )
        } catch (e: Throwable) {
            with(commandCtx) {
                finishedWithError(CommonError.fromThrowable(e))
                call.respond(
                    status = HttpStatusCode.InternalServerError,
                    message = toErrorDtoIfHas()
                )
            }
        }
    }

    override suspend fun update(call: ApplicationCall) {
        val commandCtx = RequestContext(
            requestId = call.callId.toString(),
            request = UpdateCommandRequest(requestModel = call.receive<UpdateReservationDto>().toModel()),
            stubCase = stubCase
        )

        try {
            service.update(commandCtx)
            call.respond(commandCtx.request.responseModel.toDto())
        } catch (e: Throwable) {
            with(commandCtx) {
                finishedWithError(CommonError.fromThrowable(e))
                call.respond(
                    status = HttpStatusCode.InternalServerError,
                    message = toErrorDtoIfHas()
                )
            }
        }
    }

    override suspend fun delete(call: ApplicationCall) {
        val commandCtx = RequestContext(
            requestId = call.callId.toString(),
            request = DeleteCommandRequest(requestModelId = ReservationIdModel(call.parameters["id"].toString())),
            stubCase = stubCase
        )

        try {
            service.delete(commandCtx)
            call.respond(commandCtx.request.responseModel.toDto())
        } catch (e: Throwable) {
            with(commandCtx) {
                finishedWithError(CommonError.fromThrowable(e))
                call.respond(
                    status = HttpStatusCode.InternalServerError,
                    message = toErrorDtoIfHas()
                )
            }
        }
    }

    override suspend fun findById(call: ApplicationCall) {
        val queryCtx = RequestContext(
            requestId = call.callId.toString(),
            request = FindByIdQueryRequest(reservationId = ReservationIdModel(call.parameters["id"].toString())),
            stubCase = stubCase
        )

        try {
            service.findById(queryCtx)
            call.respond(queryCtx.request.responseModels.map { it.toDto() })
        } catch (e: Throwable) {
            with(queryCtx) {
                finishedWithError(com.deskshare.common.models.error.CommonError.fromThrowable(e))
                call.respond(
                    status = HttpStatusCode.InternalServerError,
                    message = toErrorDtoIfHas()
                )
            }
        }
    }

    override suspend fun findAll(call: ApplicationCall) {
        // todo implement filter, paging etc.
        val queryCtx = RequestContext(
            requestId = call.callId.toString(),
            request = FindByFilterQueryRequest(),
            stubCase = stubCase
        )

        try {
            service.findByFilter(queryCtx)
            call.respond(queryCtx.request.responseModels.map { it.toDto() })
        } catch (e: Throwable) {
            with(queryCtx) {
                finishedWithError(com.deskshare.common.models.error.CommonError.fromThrowable(e))
                call.respond(
                    status = HttpStatusCode.InternalServerError,
                    message = toErrorDtoIfHas()
                )
            }
        }
    }
}
