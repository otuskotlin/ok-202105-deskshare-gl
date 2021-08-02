package com.deskshare.common.models.error

class CommonError(
    override val code: ErrorCode,
    override val level: ErrorLevel,
    override val message: String
) : IError {
    companion object {
        fun fromCommon(message: String) =
            CommonError(code = ErrorCode.COMMON, message = message, level = ErrorLevel.ERROR)
        fun fromValidation(message: String) =
            CommonError(code = ErrorCode.VALIDATION, message = message, level = ErrorLevel.ERROR)
        fun fromRuntime(message: String) =
            CommonError(code = ErrorCode.RUNTIME, message = message, level = ErrorLevel.ERROR)
    }
}
