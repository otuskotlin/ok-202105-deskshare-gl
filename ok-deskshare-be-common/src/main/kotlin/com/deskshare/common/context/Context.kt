package com.deskshare.common.context

import com.deskshare.common.models.error.IError
import java.time.LocalDateTime

class Context private constructor(
    val requestId: String,
    val requestStartedAt: LocalDateTime,
    val requestLocale: LocaleModel,
    val errors: MutableList<IError>,
    val status: IContextStatus
) {
    fun isSuccess() = status is ContextStatusSuccess
    fun isFailed() = status is ContextStatusFailure
    fun isPending() = status is ContextStatusPending

    data class Builder(
        var requestId: String = "",
        var requestStartedAt: LocalDateTime = LocalDateTime.now(),
        var requestLocale: LocaleModel = LocaleModel.EN,
        var errors: MutableList<IError> = mutableListOf(),
        var status: IContextStatus = ContextStatusPending
    ) {
        fun withRequestId(id: String) = apply { this.requestId = id }
        fun withRequestLocale(locale: LocaleModel) = apply { this.requestLocale = locale }
        fun withStatus(status: IContextStatus) = apply { this.status = status }
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
