package com.deskshare.logics.worker.validation

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.command.UpdateCommandRequest
import com.deskshare.cor.dsl.CorChainDslInterface
import com.deskshare.cor.dsl.worker
import com.deskshare.logics.validation.UpdateReservationValidator

internal fun CorChainDslInterface<RequestContext<UpdateCommandRequest>>.updateChainValidation() {
    worker {
        supports { isRunning() }
        handle {
            val validationResult = UpdateReservationValidator().validate(this)
            validationResult.mapIntoRequestContext(this)
        }
    }
}
