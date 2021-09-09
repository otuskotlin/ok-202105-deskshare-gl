package com.deskshare.common.context

import com.deskshare.common.models.error.ErrorInterface
import java.time.Instant

data class RequestContext<T: RequestInterface>(
    val requestId: String = "",
    val request: T,
    val stubCase: Boolean = false
) {
    var status = RequestContextStatus.None
    val requestStartedAt: Instant = Instant.now()
    var errors: MutableList<ErrorInterface> = mutableListOf()
        private set

    fun isStubCase() = stubCase
    fun isSuccess() = status == RequestContextStatus.Success
    fun isRunning() = status == RequestContextStatus.Running

    fun addError(error: ErrorInterface) {
        errors.add(error)
        status = RequestContextStatus.Failing
    }

    fun finishedOk() {
        status = RequestContextStatus.Success
    }

    fun finishedWithError(error: ErrorInterface) {
        addError(error)
    }
}
