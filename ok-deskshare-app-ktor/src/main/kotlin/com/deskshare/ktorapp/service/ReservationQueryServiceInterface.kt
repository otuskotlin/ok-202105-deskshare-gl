package com.deskshare.ktorapp.service

import com.deskshare.common.context.QueryContext
import com.deskshare.common.context.query.FindByFilterQuery
import com.deskshare.common.context.query.FindByIdQuery

interface ReservationQueryServiceInterface {
    fun <T : FindByIdQuery> findById(ctx: QueryContext<T>): QueryContext<T>
    fun <T : FindByFilterQuery> findByFilter(ctx: QueryContext<T>): QueryContext<T>
}
