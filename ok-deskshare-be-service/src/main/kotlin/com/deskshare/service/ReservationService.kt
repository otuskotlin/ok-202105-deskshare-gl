package com.deskshare.service

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.command.CreateCommandRequest
import com.deskshare.common.context.command.DeleteCommandRequest
import com.deskshare.common.context.command.UpdateCommandRequest
import com.deskshare.common.context.query.FindByFilterQueryRequest
import com.deskshare.common.context.query.FindByIdQueryRequest
import com.deskshare.logics.ReservationManager

class ReservationService : ReservationServiceInterface {
    private val manager: ReservationManager = ReservationManager()

    override suspend fun create(ctx: RequestContext<CreateCommandRequest>) {
        manager.create(ctx)
    }

    override suspend fun update(ctx: RequestContext<UpdateCommandRequest>) {
        manager.update(ctx)
    }

    override suspend fun delete(ctx: RequestContext<DeleteCommandRequest>) {
        manager.delete(ctx)
    }

    override suspend fun findById(ctx: RequestContext<FindByIdQueryRequest>) {
        manager.findById(ctx)
    }

    override suspend fun findByFilter(ctx: RequestContext<FindByFilterQueryRequest>) {
        manager.findByFilter(ctx)
    }
}
