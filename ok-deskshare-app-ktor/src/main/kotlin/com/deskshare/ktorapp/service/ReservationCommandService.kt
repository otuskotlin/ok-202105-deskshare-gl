package com.deskshare.ktorapp.service

import com.deskshare.common.context.CommandContext
import com.deskshare.common.context.command.CreateCommand
import com.deskshare.common.context.command.DeleteCommand
import com.deskshare.common.context.command.UpdateCommand
import com.deskshare.common.models.error.CommonError
import com.deskshare.stubs.Reservation

class ReservationCommandService : ReservationCommandServiceInterface {
    override fun <T : CreateCommand> create(ctx: CommandContext<T>): CommandContext<T> {
        return ctx.apply {
            responseReservation = Reservation.getModel()
            finishedOk()
        }
    }

    override fun <T : UpdateCommand> update(ctx: CommandContext<T>): CommandContext<T> {
        return if (Reservation.isCorrectId(ctx.command.oldModel.id.id)) {
            ctx.apply {
                responseReservation = command.oldModel
                finishedOk()
            }
        } else {
            ctx.apply {
                finishedWithError(CommonError.fromNotFound("Unknown reservation id ${ctx.command.oldModel.id.id}"))
            }
        }
    }

    override fun <T : DeleteCommand> delete(ctx: CommandContext<T>): CommandContext<T> {
        return if (Reservation.isCorrectId(ctx.command.reservationId.id)) {
            ctx.apply {
                responseReservation = Reservation.getCanceledModel()
                finishedOk()
            }
        } else {
            ctx.apply {
                finishedWithError(CommonError.fromNotFound("Unknown reservation id ${ctx.command.reservationId.id}"))
            }
        }
    }
}
