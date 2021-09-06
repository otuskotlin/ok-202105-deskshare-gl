package com.deskshare.service

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.command.CreateCommandRequest
import com.deskshare.common.context.command.DeleteCommandRequest
import com.deskshare.common.context.command.UpdateCommandRequest
import com.deskshare.common.context.query.FindByFilterQueryRequest
import com.deskshare.common.context.query.FindByIdQueryRequest

interface ReservationServiceInterface {
    suspend fun <T : CreateCommandRequest> create(ctx: RequestContext<T>): RequestContext<T>
    suspend fun <T : UpdateCommandRequest> update(ctx: RequestContext<T>): RequestContext<T>
    suspend fun <T : DeleteCommandRequest> delete(ctx: RequestContext<T>): RequestContext<T>

    suspend fun <T : FindByIdQueryRequest> findById(ctx: RequestContext<T>): RequestContext<T>
    suspend fun <T : FindByFilterQueryRequest> findByFilter(ctx: RequestContext<T>): RequestContext<T>
}
