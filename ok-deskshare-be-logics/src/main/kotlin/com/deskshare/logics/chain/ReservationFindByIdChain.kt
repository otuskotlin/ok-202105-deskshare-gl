package com.deskshare.logics.chain

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.query.FindByIdQueryRequest
import com.deskshare.cor.CorExecInterface
import com.deskshare.cor.dsl.chain
import com.deskshare.logics.chain.stub.reservationFindByIdStub
import com.deskshare.logics.config.chainConfiguration
import com.deskshare.logics.worker.finishChainWorker
import com.deskshare.logics.worker.initChainWorker

object ReservationFindByIdChain :
    CorExecInterface<RequestContext<FindByIdQueryRequest>> by chain<RequestContext<FindByIdQueryRequest>>({
        chainConfiguration()
        initChainWorker(title = "Init find by filter chain")
        reservationFindByIdStub()
        finishChainWorker()
    }).build()
