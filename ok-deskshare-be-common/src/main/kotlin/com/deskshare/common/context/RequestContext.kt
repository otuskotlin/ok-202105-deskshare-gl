package com.deskshare.common.context

import com.deskshare.common.models.error.ErrorInterface
import java.time.Instant

data class RequestContext<T: RequestInterface>(
    val requestId: String = "",
    val request: T,
    var stubCase: Boolean = false
) {
    private var status = RequestContextStatus.Pending
    private val requestStartedAt: Instant = Instant.now()
    var errors: MutableList<ErrorInterface> = mutableListOf()
        private set

    fun isStubCase() = stubCase
    fun isSuccess() = status == RequestContextStatus.Success
    fun isFailed() = status == RequestContextStatus.Failure
    fun isPending() = status == RequestContextStatus.Pending

    fun addError(error: ErrorInterface) {
        errors.add(error)
        status = RequestContextStatus.Failure
    }

    fun finishedOk() {
        status = RequestContextStatus.Success
    }

    fun finishedWithError(error: ErrorInterface) {
        addError(error)
    }
}
