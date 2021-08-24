package com.deskshare.common.context

import com.deskshare.common.context.command.CommandInterface
import com.deskshare.common.models.ReservationModel

class CommandContext<T : CommandInterface>(
    val command: T,
    requestId: String = "",
    requestLocale: LocaleModel = LocaleModel.EN
) : Context(requestId, requestLocale) {
    var responseReservation: ReservationModel = ReservationModel()
}

