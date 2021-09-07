package com.deskshare.logics.chain.stub

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.RequestContextStatus
import com.deskshare.common.context.query.FindByIdQueryRequest
import com.deskshare.cor.dsl.CorExecDslInterface
import com.deskshare.cor.dsl.worker
import com.deskshare.stubs.ReservationStub

object ReservationFindByIdStub :
    CorExecDslInterface<RequestContext<FindByIdQueryRequest>> by worker({
        title = "init stub data for one model"
        supports { isStubCase() && status == RequestContextStatus.Running }
        handle {
            request.responseModels.add(ReservationStub.getModel())
            status = RequestContextStatus.Finishing
        }
    })

