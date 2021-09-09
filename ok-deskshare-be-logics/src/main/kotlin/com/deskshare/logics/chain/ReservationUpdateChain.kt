package com.deskshare.logics.chain

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.command.UpdateCommandRequest
import com.deskshare.cor.CorExecInterface
import com.deskshare.cor.dsl.chain
import com.deskshare.logics.chain.stub.reservationUpdateStub
import com.deskshare.logics.config.chainConfiguration
import com.deskshare.logics.worker.finishChainWorker
import com.deskshare.logics.worker.initChainWorker
import com.deskshare.logics.worker.validation.updateChainValidation

object ReservationUpdateChain :
    CorExecInterface<RequestContext<UpdateCommandRequest>> by chain<RequestContext<UpdateCommandRequest>>({
        chainConfiguration()
        initChainWorker(title = "Init update chain")
        updateChainValidation()
        reservationUpdateStub()
        finishChainWorker()
    }).build()
