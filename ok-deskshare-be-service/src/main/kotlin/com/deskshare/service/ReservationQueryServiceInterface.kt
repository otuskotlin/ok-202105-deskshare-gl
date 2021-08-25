package com.deskshare.service

import com.deskshare.common.context.QueryRequestContext
import com.deskshare.common.context.query.FindByFilterQuery
import com.deskshare.common.context.query.FindByIdQuery
import com.deskshare.common.models.ReservationModel

interface ReservationQueryServiceInterface {
    suspend fun <T : FindByIdQuery> findById(ctx: QueryRequestContext<T>): ReservationModel
    suspend fun <T : FindByFilterQuery> findByFilter(ctx: QueryRequestContext<T>): List<ReservationModel>
}
