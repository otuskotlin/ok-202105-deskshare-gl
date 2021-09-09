package com.deskshare.logics.chain

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.command.DeleteCommandRequest
import com.deskshare.cor.CorExecInterface
import com.deskshare.cor.dsl.chain
import com.deskshare.logics.chain.stub.reservationDeleteStub
import com.deskshare.logics.config.chainConfiguration
import com.deskshare.logics.worker.finishChainWorker
import com.deskshare.logics.worker.initChainWorker
import com.deskshare.logics.worker.validation.deleteChainValidation

object ReservationDeleteChain :
    CorExecInterface<RequestContext<DeleteCommandRequest>> by chain<RequestContext<DeleteCommandRequest>>({
        chainConfiguration()
        initChainWorker(title = "Init delete chain")
        deleteChainValidation()
        reservationDeleteStub()
        finishChainWorker()
    }).build()
