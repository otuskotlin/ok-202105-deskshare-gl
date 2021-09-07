package com.deskshare.service

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.command.CreateCommandRequest
import com.deskshare.common.context.command.DeleteCommandRequest
import com.deskshare.common.context.command.UpdateCommandRequest
import com.deskshare.common.context.query.FindByFilterQueryRequest
import com.deskshare.common.context.query.FindByIdQueryRequest
import com.deskshare.stubs.ReservationStub

class ReservationService : ReservationServiceInterface {
    override suspend fun create(ctx: RequestContext<CreateCommandRequest>) {
        ctx.apply {
            request.responseModel = ReservationStub.getModel()
            finishedOk()
        }
    }

    override suspend fun update(ctx: RequestContext<UpdateCommandRequest>) {
        ctx.apply {
            request.responseModel = ReservationStub.getModel()
            finishedOk()
        }
    }

    override suspend fun delete(ctx: RequestContext<DeleteCommandRequest>) {
        ctx.apply {
            request.responseModel = ReservationStub.getCanceledModel()
            finishedOk()
        }
    }

    override suspend fun findById(ctx: RequestContext<FindByIdQueryRequest>) {
        ctx.apply {
            request.responseModels.add(ReservationStub.getModel())
            finishedOk()
        }
    }

    override suspend fun findByFilter(ctx: RequestContext<FindByFilterQueryRequest>) {
        ctx.apply {
            request.responseModels.addAll(ReservationStub.getModels())
            finishedOk()
        }
    }
}
