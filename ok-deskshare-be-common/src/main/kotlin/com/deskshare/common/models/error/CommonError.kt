package com.deskshare.common.models.error

class CommonError(
    override val code: ErrorCode,
    override val level: ErrorLevel,
    override val message: String
) : IError {
    companion object {
        fun fromCommon(message: String) =
            CommonError(code = ErrorCode.Common, message = message, level = ErrorLevel.ERROR)
        fun fromValidation(message: String) =
            CommonError(code = ErrorCode.Validation, message = message, level = ErrorLevel.ERROR)
        fun fromRuntime(message: String) =
            CommonError(code = ErrorCode.Runtime, message = message, level = ErrorLevel.ERROR)
    }
}
