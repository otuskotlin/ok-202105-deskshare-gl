package com.deskshare.logics.worker.validation

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.command.DeleteCommandRequest
import com.deskshare.cor.dsl.CorChainDslInterface
import com.deskshare.cor.dsl.worker
import com.deskshare.logics.validation.DeleteReservationValidator

internal fun CorChainDslInterface<RequestContext<DeleteCommandRequest>>.deleteChainValidation() {
    worker {
        supports { isRunning() }
        handle {
            val validationResult = DeleteReservationValidator().validate(this)
            validationResult.mapIntoRequestContext(this)
        }
    }
}
