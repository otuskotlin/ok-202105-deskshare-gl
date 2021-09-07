package com.deskshare.logics

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.command.CreateCommandRequest
import com.deskshare.logics.chain.ReservationCreateChain

class ReservationManager {
    suspend fun create(ctx: RequestContext<CreateCommandRequest>) {
        ReservationCreateChain.exec(ctx)
    }
}
