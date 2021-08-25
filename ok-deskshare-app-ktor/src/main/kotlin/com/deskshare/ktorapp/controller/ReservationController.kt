package com.deskshare.ktorapp.controller

import com.deskshare.common.context.CommandContext
import com.deskshare.common.context.QueryContext
import com.deskshare.dto.mapping.rest.toDto
import com.deskshare.dto.mapping.rest.toErrorDtoIfHave
import com.deskshare.dto.mapping.rest.toModel
import com.deskshare.ktorapp.service.ReservationCommandServiceInterface
import com.deskshare.ktorapp.service.ReservationQueryServiceInterface
import com.deskshare.ktorapp.service.toHttpStatusCode
import com.deskshare.openapi.models.CreateReservationDto
import com.deskshare.openapi.models.UpdateReservationDto
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*

class ReservationController(
    private val commandService: ReservationCommandServiceInterface,
    private val queryService: ReservationQueryServiceInterface
) {
    suspend fun create(call: ApplicationCall) {
        val newReservation = call.receive<CreateReservationDto>().toModel()

        val ctx = commandService.create(CommandContext.forCreateReservation(newReservation))
        call.respond(
            status = HttpStatusCode.Created,
            message = ctx.responseReservation.toDto()
        )
    }

    suspend fun update(call: ApplicationCall) {
        val oldReservation = call.receive<UpdateReservationDto>().toModel()
        val ctx = commandService.update(CommandContext.forUpdateReservation(oldReservation))

        if (ctx.isSuccess()) {
            call.respond(ctx.responseReservation.toDto())
        } else {
            call.respond(
                status = ctx.toHttpStatusCode(),
                message = ctx.toErrorDtoIfHave()
            )
        }
    }

    suspend fun delete(call: ApplicationCall) {
        val id = call.parameters["id"].toString()

        val ctx = commandService.delete(CommandContext.forDeleteReservation(id))
        if (ctx.isSuccess()) {
            call.respond(ctx.responseReservation.toDto())
        } else {
            call.respond(
                status = ctx.toHttpStatusCode(),
                message = ctx.toErrorDtoIfHave()
            )
        }
    }

    suspend fun findById(call: ApplicationCall) {
        val id = call.parameters["id"].toString()

        val ctx = queryService.findById(QueryContext.forFindById(id))
        if (ctx.isSuccess()) {
            call.respond(ctx.responseReservations.map { it.toDto() })
        } else {
            call.respond(
                status = ctx.toHttpStatusCode(),
                message = ctx.toErrorDtoIfHave()
            )
        }
    }

    suspend fun findAll(call: ApplicationCall) {
        // todo implement filter, paging etc.
        val ctx = queryService.findByFilter(QueryContext.forFindByFilter())
        call.respond(ctx.responseReservations.map { it.toDto() })
    }
}
