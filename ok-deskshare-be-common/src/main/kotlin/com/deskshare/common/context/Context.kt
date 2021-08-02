package com.deskshare.common.context

import com.deskshare.common.models.error.IError
import com.deskshare.common.models.ReservationModel
import java.time.LocalDateTime

data class Context(
    val requestId: String = "",
    val requestStartedAt: LocalDateTime = LocalDateTime.now(),
    val requestLocale: LocaleModel = LocaleModel.EN,
    val requestReservation: ReservationModel = ReservationModel(),
    val responseReservation: ReservationModel = ReservationModel(),
    val errors: MutableList<IError> = mutableListOf(),
    val status: String
)
