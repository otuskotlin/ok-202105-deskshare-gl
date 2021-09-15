package com.deskshare.logics.worker.validation

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.command.CreateCommandRequest
import com.deskshare.common.models.error.CommonError
import com.deskshare.common.models.error.ErrorCode
import com.deskshare.common.models.error.ErrorLevel
import com.deskshare.cor.dsl.CorChainDslInterface
import com.deskshare.cor.dsl.worker
import com.deskshare.logics.validation.CreateReservationValidator
import com.deskshare.validation.ValidationFieldErrorInterface

internal fun CorChainDslInterface<RequestContext<CreateCommandRequest>>.createChainValidation() {
    worker {
        supports { isRunning() }
        handle {
            val validationResult = CreateReservationValidator().validate(this)
            validationResult.mapIntoRequestContext(this)
        }
    }
}
