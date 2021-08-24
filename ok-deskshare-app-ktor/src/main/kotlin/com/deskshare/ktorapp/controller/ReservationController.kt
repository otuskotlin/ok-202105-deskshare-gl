package com.deskshare.ktorapp.controller

import com.deskshare.common.context.CommandContext
import com.deskshare.common.context.QueryContext
import com.deskshare.common.context.command.CreateCommand
import com.deskshare.common.context.command.DeleteCommand
import com.deskshare.common.context.query.FindByFilterQuery
import com.deskshare.common.context.query.FindByIdQuery
import com.deskshare.common.models.ReservationIdModel
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
        // todo refactor next line
        val ctx = commandService.create(CommandContext(CreateCommand(newReservation)))
        call.respond(
            status = HttpStatusCode.Created,
            message = ctx.responseReservation.toDto()
        )
    }

    suspend fun update(call: ApplicationCall) {
        val oldReservation = call.receive<UpdateReservationDto>().toModel()
        // todo refactor next line
        val ctx = commandService.create(CommandContext(CreateCommand(oldReservation)))
        call.respond(ctx.responseReservation.toDto())
    }

    suspend fun delete(call: ApplicationCall) {
        val id = call.parameters["id"].toString()
        // todo refactor next line
        val ctx = commandService.delete(CommandContext(DeleteCommand(ReservationIdModel(id))))
        call.respond(ctx.responseReservation.toDto())
    }

    suspend fun findById(call: ApplicationCall) {
        val id = call.parameters["id"].toString()
        val ctx = queryService.findById(QueryContext(FindByIdQuery(ReservationIdModel(id))))

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
        val ctx = queryService.findByFilter(QueryContext(FindByFilterQuery()))
        call.respond(ctx.responseReservations.map { it.toDto() })
    }
}
