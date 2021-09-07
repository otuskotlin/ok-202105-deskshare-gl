package com.deskshare.logics.chain

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.command.UpdateCommandRequest
import com.deskshare.cor.CorExecInterface
import com.deskshare.cor.dsl.chain
import com.deskshare.cor.dsl.configuration
import com.deskshare.logics.chain.stub.ReservationUpdateStub
import com.deskshare.logics.worker.FinishWorker
import com.deskshare.logics.worker.InitWorker

object ReservationUpdateChain :
    CorExecInterface<RequestContext<UpdateCommandRequest>> by chain<RequestContext<UpdateCommandRequest>>({
        configuration {
            logging = true
        }

        add(InitWorker())

        add(ReservationUpdateStub)

        add(FinishWorker())

    }).build()
