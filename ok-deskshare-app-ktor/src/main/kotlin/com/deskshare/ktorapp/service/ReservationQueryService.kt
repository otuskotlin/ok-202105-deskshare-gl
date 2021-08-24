package com.deskshare.ktorapp.service

import com.deskshare.common.context.QueryContext
import com.deskshare.common.context.query.FindByFilterQuery
import com.deskshare.common.context.query.FindByIdQuery
import com.deskshare.common.models.error.CommonError
import com.deskshare.stubs.Reservation

class ReservationQueryService : ReservationQueryServiceInterface {
    override fun <T : FindByIdQuery> findById(ctx: QueryContext<T>): QueryContext<T> {
        return if (Reservation.isCorrectId(ctx.query.reservationId.id)) {
            ctx.apply {
                responseReservations.add(Reservation.getModel())
                finishedOk()
            }
        } else {
            ctx.apply {
                finishedWithError(CommonError.fromNotFound("Unknown reservation id ${ctx.query.reservationId.id}"))
            }
        }
    }

    override fun <T : FindByFilterQuery> findByFilter(ctx: QueryContext<T>): QueryContext<T> {
        return ctx.apply {
            responseReservations = Reservation.getModels().toMutableList()
            finishedOk()
        }
    }
}
