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
    val status: ContextStatus = ContextStatus.STARTED
) {
    fun withRequestId(id: String) = copy(requestId = id)

    fun withRequestLocale(locale: LocaleModel) = copy(requestLocale = locale)

    fun withStatus(status: ContextStatus) = copy(status = status)

    fun withError(error: IError): Context {
        errors.add(error)
        return this
    }

}
