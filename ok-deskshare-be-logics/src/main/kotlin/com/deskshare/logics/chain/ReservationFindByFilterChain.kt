package com.deskshare.logics.chain

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.command.CreateCommandRequest
import com.deskshare.common.context.query.FindByFilterQueryRequest
import com.deskshare.common.context.query.FindByIdQueryRequest
import com.deskshare.cor.CorExecInterface
import com.deskshare.cor.dsl.chain
import com.deskshare.cor.dsl.configuration
import com.deskshare.logics.chain.stub.ReservationCreateStub
import com.deskshare.logics.chain.stub.ReservationFindByFilterStub
import com.deskshare.logics.chain.stub.ReservationFindByIdStub
import com.deskshare.logics.worker.FinishWorker
import com.deskshare.logics.worker.InitWorker

object ReservationFindByFilterChain :
    CorExecInterface<RequestContext<FindByFilterQueryRequest>> by chain<RequestContext<FindByFilterQueryRequest>>({
        configuration {
            logging = true
        }

        add(InitWorker())

        add(ReservationFindByFilterStub)

        add(FinishWorker())

    }).build()
