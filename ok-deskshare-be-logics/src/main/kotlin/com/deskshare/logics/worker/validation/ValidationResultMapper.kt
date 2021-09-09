package com.deskshare.logics.worker.validation

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.RequestInterface
import com.deskshare.common.models.error.CommonError
import com.deskshare.common.models.error.ErrorCode
import com.deskshare.common.models.error.ErrorLevel
import com.deskshare.validation.ValidationFieldErrorInterface
import com.deskshare.validation.ValidationResult

internal fun <T : RequestInterface> ValidationResult.mapIntoRequestContext(ctx: RequestContext<T>) {
    errors.forEach {
        ctx.addError(
            CommonError(
                message = it.message,
                code = ErrorCode.Validation,
                level = ErrorLevel.ERROR,
                field = when (it) {
                    is ValidationFieldErrorInterface -> it.field
                    else -> ""
                }
            )
        )
    }
}
