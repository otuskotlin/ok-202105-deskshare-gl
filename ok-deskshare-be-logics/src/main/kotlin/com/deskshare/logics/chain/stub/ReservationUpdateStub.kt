package com.deskshare.logics.chain.stub

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.RequestContextStatus
import com.deskshare.common.context.command.UpdateCommandRequest
import com.deskshare.cor.dsl.CorChainDslInterface
import com.deskshare.cor.dsl.worker

internal fun CorChainDslInterface<RequestContext<UpdateCommandRequest>>.reservationUpdateStub() = worker {
    title = "init stub data for update"
    supports { isStubCase() && status == RequestContextStatus.Running }
    handle {
        request.responseModel = request.requestModel
        status = RequestContextStatus.Finishing
    }
}
