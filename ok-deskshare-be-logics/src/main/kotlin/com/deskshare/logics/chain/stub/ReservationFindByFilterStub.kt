package com.deskshare.logics.chain.stub

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.RequestContextStatus
import com.deskshare.common.context.query.FindByFilterQueryRequest
import com.deskshare.cor.dsl.CorExecDslInterface
import com.deskshare.cor.dsl.worker
import com.deskshare.stubs.ReservationStub

object ReservationFindByFilterStub :
    CorExecDslInterface<RequestContext<FindByFilterQueryRequest>> by worker({
        title = "init stub data for find with collection"
        supports { isStubCase() && status == RequestContextStatus.Running }
        handle {
            request.responseModels.addAll(ReservationStub.getModels())
            status = RequestContextStatus.Finishing
        }
    })

