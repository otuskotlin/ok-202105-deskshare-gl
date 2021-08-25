package com.deskshare.service

import com.deskshare.common.context.QueryRequestContext
import com.deskshare.common.context.query.FindByFilterQuery
import com.deskshare.common.context.query.FindByIdQuery
import com.deskshare.common.exception.NotFoundReservationException
import com.deskshare.common.models.ReservationModel
import com.deskshare.stubs.Reservation

class ReservationQueryService : ReservationQueryServiceInterface {
    override suspend fun <T : FindByIdQuery> findById(ctx: QueryRequestContext<T>): ReservationModel {
        if (!Reservation.isCorrectId(ctx.query.reservationId.id)) {
            throw NotFoundReservationException("Unknown reservation id ${ctx.query.reservationId.id}")
        }
        return Reservation.getModel()
    }

    override suspend fun <T : FindByFilterQuery> findByFilter(ctx: QueryRequestContext<T>): List<ReservationModel> {
        return Reservation.getModels()
    }
}
