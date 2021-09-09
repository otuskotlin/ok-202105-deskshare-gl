package com.deskshare.logics.chain

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.command.CreateCommandRequest
import com.deskshare.cor.CorExecInterface
import com.deskshare.cor.dsl.chain
import com.deskshare.logics.chain.stub.reservationCreateStub
import com.deskshare.logics.config.chainConfiguration
import com.deskshare.logics.worker.finishChainWorker
import com.deskshare.logics.worker.initChainWorker
import com.deskshare.logics.worker.validation.createChainValidation

object ReservationCreateChain :
    CorExecInterface<RequestContext<CreateCommandRequest>> by chain<RequestContext<CreateCommandRequest>>({
        chainConfiguration()
        initChainWorker(title = "Init create chain")
        createChainValidation()
        reservationCreateStub()
        finishChainWorker()
    }).build()
