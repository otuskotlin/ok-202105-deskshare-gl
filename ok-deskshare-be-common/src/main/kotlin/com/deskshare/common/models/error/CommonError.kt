package com.deskshare.common.models.error

class CommonError(
    override val code: ErrorCode,
    override val level: ErrorLevel,
    override val message: String
) : ErrorInterface {
    companion object {
        fun fromNotFound(message: String) =
            CommonError(code = ErrorCode.NotFound, message = message, level = ErrorLevel.ERROR)

        fun fromValidation(message: String) =
            CommonError(code = ErrorCode.Validation, message = message, level = ErrorLevel.ERROR)

        fun fromAuth(message: String) =
            CommonError(code = ErrorCode.Authentication, message = message, level = ErrorLevel.ERROR)

        fun fromRuntime(message: String) =
            CommonError(code = ErrorCode.Runtime, message = message, level = ErrorLevel.ERROR)
    }
}
