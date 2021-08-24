package com.deskshare.common.context

import com.deskshare.common.models.error.ErrorInterface
import java.time.Instant

abstract class Context(val requestId: String = "", val requestLocale: LocaleModel = LocaleModel.EN) {
    private var status = ContextStatus.Pending
    private val requestStartedAt: Instant = Instant.now()
    var error: ErrorInterface? = null
        private set

    fun isSuccess() = status == ContextStatus.Success
    fun isFailed() = status == ContextStatus.Failure
    fun isPending() = status == ContextStatus.Pending

    fun finishedOk() {
        status = ContextStatus.Success
    }

    fun finishedWithError(error: ErrorInterface) {
        this.error = error
        status = ContextStatus.Failure
    }
}
