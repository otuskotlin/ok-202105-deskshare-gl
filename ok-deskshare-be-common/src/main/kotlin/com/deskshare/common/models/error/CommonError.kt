package com.deskshare.common.models.error

import com.deskshare.common.exception.NotFoundReservationException

class CommonError(
    override val field: String = "",
    override val code: ErrorCode,
    override val level: ErrorLevel,
    override val message: String
) : ErrorInterface {

    companion object {
        fun fromThrowable(e: Throwable) = when (e) {
            is NotFoundReservationException -> fromNotFound(e.message.toString())
            else -> fromRuntime(e.message.toString())
        }

        fun fromNotFound(message: String) =
            CommonError(code = ErrorCode.NotFound, message = message, level = ErrorLevel.ERROR)

        fun fromValidation(field: String = "", message: String) =
            CommonError(code = ErrorCode.Validation, message = message, level = ErrorLevel.ERROR, field = "")

        fun fromAuth(message: String) =
            CommonError(code = ErrorCode.Authentication, message = message, level = ErrorLevel.ERROR)

        fun fromRuntime(message: String) =
            CommonError(code = ErrorCode.Runtime, message = message, level = ErrorLevel.ERROR)
    }
}
