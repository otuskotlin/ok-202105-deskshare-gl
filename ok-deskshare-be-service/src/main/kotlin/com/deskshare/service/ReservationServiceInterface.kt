package com.deskshare.service

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.command.CreateCommandRequest
import com.deskshare.common.context.command.DeleteCommandRequest
import com.deskshare.common.context.command.UpdateCommandRequest
import com.deskshare.common.context.query.FindByFilterQueryRequest
import com.deskshare.common.context.query.FindByIdQueryRequest
import com.deskshare.openapi.models.CreateReservationDto
import com.deskshare.openapi.models.ResponseErrorDto
import com.deskshare.openapi.models.UpdateReservationDto
import com.deskshare.openapi.models.ViewReservationDto

interface ReservationServiceInterface {
    // commands
    suspend fun create(ctx: RequestContext<CreateCommandRequest>, requestDto: CreateReservationDto): ViewReservationDto
    suspend fun update(ctx: RequestContext<UpdateCommandRequest>, requestDto: UpdateReservationDto): ViewReservationDto
    suspend fun delete(ctx: RequestContext<DeleteCommandRequest>, reservationId: String): ViewReservationDto

    // queries
    suspend fun findById(ctx: RequestContext<FindByIdQueryRequest>, reservationId: String): List<ViewReservationDto>
    suspend fun findByFilter(ctx: RequestContext<FindByFilterQueryRequest>): List<ViewReservationDto>

    fun handleError(ctx: RequestContext<*>, e: Throwable): List<ResponseErrorDto>
}
