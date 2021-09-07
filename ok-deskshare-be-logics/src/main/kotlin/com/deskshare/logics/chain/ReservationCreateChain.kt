package com.deskshare.logics.chain

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.command.CreateCommandRequest
import com.deskshare.cor.CorExecInterface
import com.deskshare.cor.dsl.chain
import com.deskshare.cor.dsl.configuration
import com.deskshare.logics.chain.stub.ReservationCreateStub
import com.deskshare.logics.worker.FinishWorker
import com.deskshare.logics.worker.InitWorker

object ReservationCreateChain :
    CorExecInterface<RequestContext<CreateCommandRequest>> by chain<RequestContext<CreateCommandRequest>>({
        configuration {
            logging = true
        }

        add(InitWorker())

        add(ReservationCreateStub)

        add(FinishWorker())

    }).build()
