package com.deskshare.logics.chain

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.command.DeleteCommandRequest
import com.deskshare.cor.CorExecInterface
import com.deskshare.cor.dsl.chain
import com.deskshare.cor.dsl.configuration
import com.deskshare.logics.chain.stub.ReservationDeleteStub
import com.deskshare.logics.worker.FinishWorker
import com.deskshare.logics.worker.InitWorker

object ReservationDeleteChain :
    CorExecInterface<RequestContext<DeleteCommandRequest>> by chain<RequestContext<DeleteCommandRequest>>({
        configuration {
            logging = true
        }

        add(InitWorker())

        add(ReservationDeleteStub)

        add(FinishWorker())

    }).build()
