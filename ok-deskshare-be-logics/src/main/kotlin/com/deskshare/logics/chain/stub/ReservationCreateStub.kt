package com.deskshare.logics.chain.stub

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.RequestContextStatus
import com.deskshare.common.context.command.CreateCommandRequest
import com.deskshare.cor.dsl.CorChainDslInterface
import com.deskshare.cor.dsl.worker
import com.deskshare.stubs.ReservationStub

internal fun CorChainDslInterface<RequestContext<CreateCommandRequest>>.reservationCreateStub() = worker {
    title = "init stub data"
    supports { isStubCase() && status == RequestContextStatus.Running }
    handle {
        request.responseModel = ReservationStub.getModel()
        status = RequestContextStatus.Finishing
    }
}
