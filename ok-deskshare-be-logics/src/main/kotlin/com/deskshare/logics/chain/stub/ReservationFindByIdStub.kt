package com.deskshare.logics.chain.stub

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.RequestContextStatus
import com.deskshare.common.context.query.FindByIdQueryRequest
import com.deskshare.cor.dsl.CorChainDslInterface
import com.deskshare.cor.dsl.worker
import com.deskshare.stubs.ReservationStub

internal fun CorChainDslInterface<RequestContext<FindByIdQueryRequest>>.reservationFindByIdStub() = worker {
    title = "init stub data for one model"
    supports { isStubCase() && status == RequestContextStatus.Running }
    handle {
        request.responseModels.add(ReservationStub.getModel())
        status = RequestContextStatus.Finishing
    }
}
