package com.deskshare.logics.chain.stub

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.RequestContextStatus
import com.deskshare.common.context.command.DeleteCommandRequest
import com.deskshare.cor.dsl.CorExecDslInterface
import com.deskshare.cor.dsl.worker
import com.deskshare.stubs.ReservationStub

object ReservationDeleteStub :
    CorExecDslInterface<RequestContext<DeleteCommandRequest>> by worker({
        title = "init stub data for delete by id"
        supports { isStubCase() && status == RequestContextStatus.Running }
        handle {
            request.responseModel = ReservationStub.getCanceledModel()
            status = RequestContextStatus.Finishing
        }
    })

