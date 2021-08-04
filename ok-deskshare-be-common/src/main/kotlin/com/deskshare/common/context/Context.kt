package com.deskshare.common.context

import com.deskshare.common.models.error.IError
import java.time.LocalDateTime

class Context private constructor(
    val requestId: String,
    val requestStartedAt: LocalDateTime,
    val requestLocale: LocaleModel,
    val errors: MutableList<IError>,
    val status: ContextStatus
) {
    data class Builder(
        var requestId: String = "",
        var requestStartedAt: LocalDateTime = LocalDateTime.now(),
        var requestLocale: LocaleModel = LocaleModel.EN,
        var errors: MutableList<IError> = mutableListOf(),
        var status: ContextStatus = ContextStatus.STARTED
    ) {
        fun withRequestId(id: String) = apply { this.requestId = id }
        fun withRequestLocale(locale: LocaleModel) = apply { this.requestLocale = locale }
        fun withStatus(status: ContextStatus) = apply { this.status = status }
        fun withError(error: IError) = apply { this.errors.add(error) }

        fun build() = Context(
            requestId = requestId,
            requestStartedAt = LocalDateTime.now(),
            requestLocale = requestLocale,
            errors = errors,
            status = status
        )
    }
}
