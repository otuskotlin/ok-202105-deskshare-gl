package com.deskshare.service

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.command.CreateCommandRequest
import com.deskshare.common.context.command.DeleteCommandRequest
import com.deskshare.common.context.command.UpdateCommandRequest
import com.deskshare.common.context.query.FindByFilterQueryRequest
import com.deskshare.common.context.query.FindByIdQueryRequest
import com.deskshare.stubs.Reservation

class ReservationService : ReservationServiceInterface {
    override suspend fun <T : CreateCommandRequest> create(ctx: RequestContext<T>): RequestContext<T> = ctx.apply {
        request.responseModel = Reservation.getModel()
        finishedOk()
    }

    override suspend fun <T : UpdateCommandRequest> update(ctx: RequestContext<T>): RequestContext<T> = ctx.apply {
        request.responseModel = Reservation.getModel()
        finishedOk()
    }

    override suspend fun <T : DeleteCommandRequest> delete(ctx: RequestContext<T>): RequestContext<T> = ctx.apply {
        request.responseModel = Reservation.getCanceledModel()
        finishedOk()
    }

    override suspend fun <T : FindByIdQueryRequest> findById(ctx: RequestContext<T>): RequestContext<T> = ctx.apply {
        request.responseModels.add(Reservation.getModel())
        finishedOk()
    }

    override suspend fun <T : FindByFilterQueryRequest> findByFilter(ctx: RequestContext<T>): RequestContext<T> = ctx.apply {
        request.responseModels.addAll(Reservation.getModels())
        finishedOk()
    }
}
