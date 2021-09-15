package com.deskshare.logics.chain

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.query.FindByFilterQueryRequest
import com.deskshare.cor.CorExecInterface
import com.deskshare.cor.dsl.chain
import com.deskshare.logics.chain.stub.reservationFindByFilterStub
import com.deskshare.logics.config.chainConfiguration
import com.deskshare.logics.worker.finishChainWorker
import com.deskshare.logics.worker.initChainWorker

object ReservationFindByFilterChain :
    CorExecInterface<RequestContext<FindByFilterQueryRequest>> by chain<RequestContext<FindByFilterQueryRequest>>({
        chainConfiguration()
        initChainWorker(title = "Init find by id chain")
        reservationFindByFilterStub()
        finishChainWorker()
    }).build()
