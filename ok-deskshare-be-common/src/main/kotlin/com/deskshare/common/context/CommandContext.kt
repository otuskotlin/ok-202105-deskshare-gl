package com.deskshare.common.context

import com.deskshare.common.context.command.CommandInterface
import com.deskshare.common.context.command.CreateCommand
import com.deskshare.common.context.command.DeleteCommand
import com.deskshare.common.context.command.UpdateCommand
import com.deskshare.common.models.ReservationIdModel
import com.deskshare.common.models.ReservationModel

class CommandContext<T : CommandInterface>(
    val command: T,
    requestId: String = "",
    requestLocale: LocaleModel = LocaleModel.EN
) : Context(requestId, requestLocale) {
    var responseReservation: ReservationModel = ReservationModel()

    companion object {
        fun forCreateReservation(newModel: ReservationModel) = CommandContext(CreateCommand(newModel))
        fun forUpdateReservation(oldModel: ReservationModel) = CommandContext(UpdateCommand(oldModel))
        fun forDeleteReservation(id: String) = CommandContext(DeleteCommand(ReservationIdModel(id)))
    }
}

