package com.deskshare.service

import com.deskshare.common.context.CommandRequestContext
import com.deskshare.common.context.command.CreateCommand
import com.deskshare.common.context.command.DeleteCommand
import com.deskshare.common.context.command.UpdateCommand
import com.deskshare.common.exception.NotFoundReservationException
import com.deskshare.common.models.ReservationModel
import com.deskshare.stubs.Reservation

class ReservationCommandService : ReservationCommandServiceInterface {
    override suspend fun <T : CreateCommand> create(ctx: CommandRequestContext<T>): ReservationModel {
        ctx.finishedOk()
        return Reservation.getModel()
    }

    override suspend fun <T : UpdateCommand> update(ctx: CommandRequestContext<T>): ReservationModel {
        if (!Reservation.isCorrectId(ctx.command.oldModel.id.id)) {
            throw NotFoundReservationException("Unknown reservation id ${ctx.command.oldModel.id.id}")
        }
        return Reservation.getModel()
    }

    override suspend fun <T : DeleteCommand> delete(ctx: CommandRequestContext<T>): ReservationModel {
        if (!Reservation.isCorrectId(ctx.command.reservationId.id)) {
            throw NotFoundReservationException("Unknown reservation id ${ctx.command.reservationId.id}")
        }
        return Reservation.getCanceledModel()
    }
}
