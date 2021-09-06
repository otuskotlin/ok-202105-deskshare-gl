package com.deskshare.service

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.command.CreateCommandRequest
import com.deskshare.common.context.command.DeleteCommandRequest
import com.deskshare.common.context.command.UpdateCommandRequest
import com.deskshare.common.context.query.FindByFilterQueryRequest
import com.deskshare.common.context.query.FindByIdQueryRequest

interface ReservationServiceInterface {
    // commands
    suspend fun create(ctx: RequestContext<CreateCommandRequest>)
    suspend fun update(ctx: RequestContext<UpdateCommandRequest>)
    suspend fun delete(ctx: RequestContext<DeleteCommandRequest>)

    // queries
    suspend fun findById(ctx: RequestContext<FindByIdQueryRequest>)
    suspend fun findByFilter(ctx: RequestContext<FindByFilterQueryRequest>)
}
