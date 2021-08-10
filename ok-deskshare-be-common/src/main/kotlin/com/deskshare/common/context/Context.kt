package com.deskshare.common.context

import com.deskshare.common.models.error.IError
import java.time.LocalDateTime

class Context private constructor(
    var requestId: String,
    var requestStartedAt: LocalDateTime,
    var requestLocale: LocaleModel,
    var errors: MutableList<IError>,
    var status: ContextStatus
) {
    fun isSuccess() = status == ContextStatus.Success
    fun isFailed() = status == ContextStatus.Failure
    fun isPending() = status == ContextStatus.Pending
}
