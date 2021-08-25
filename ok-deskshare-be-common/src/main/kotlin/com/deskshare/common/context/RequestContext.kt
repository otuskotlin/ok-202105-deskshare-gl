package com.deskshare.common.context

import com.deskshare.common.models.error.ErrorInterface
import java.time.Instant

abstract class RequestContext(val requestId: String = "") {
    private var status = RequestContextStatus.Pending
    private val requestStartedAt: Instant = Instant.now()
    var error: ErrorInterface? = null
        private set

    fun isSuccess() = status == RequestContextStatus.Success
    fun isFailed() = status == RequestContextStatus.Failure
    fun isPending() = status == RequestContextStatus.Pending

    fun finishedOk() {
        status = RequestContextStatus.Success
    }

    fun finishedWithError(error: ErrorInterface) {
        this.error = error
        status = RequestContextStatus.Failure
    }
}
