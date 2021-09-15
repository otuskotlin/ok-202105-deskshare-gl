package com.deskshare.logics

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.command.CreateCommandRequest
import com.deskshare.common.context.command.DeleteCommandRequest
import com.deskshare.common.context.command.UpdateCommandRequest
import com.deskshare.common.context.query.FindByFilterQueryRequest
import com.deskshare.common.context.query.FindByIdQueryRequest
import com.deskshare.logics.chain.*

class ReservationManager {
    suspend fun create(ctx: RequestContext<CreateCommandRequest>) {
        ReservationCreateChain.exec(ctx)
    }

    suspend fun update(ctx: RequestContext<UpdateCommandRequest>) {
        ReservationUpdateChain.exec(ctx)
    }

    suspend fun delete(ctx: RequestContext<DeleteCommandRequest>) {
        ReservationDeleteChain.exec(ctx)
    }

    suspend fun findById(ctx: RequestContext<FindByIdQueryRequest>) {
        ReservationFindByIdChain.exec(ctx)
    }

    suspend fun findByFilter(ctx: RequestContext<FindByFilterQueryRequest>) {
        ReservationFindByFilterChain.exec(ctx)
    }
}
