package com.deskshare.service

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.command.CreateCommandRequest
import com.deskshare.common.context.command.DeleteCommandRequest
import com.deskshare.common.context.command.UpdateCommandRequest
import com.deskshare.common.context.query.FindByFilterQueryRequest
import com.deskshare.common.context.query.FindByIdQueryRequest
import com.deskshare.common.models.ReservationIdModel
import com.deskshare.common.models.error.CommonError
import com.deskshare.dto.mapping.rest.toDto
import com.deskshare.dto.mapping.rest.toErrorDto
import com.deskshare.dto.mapping.rest.toModel
import com.deskshare.logics.ReservationManager
import com.deskshare.openapi.models.CreateReservationDto
import com.deskshare.openapi.models.ResponseErrorDto
import com.deskshare.openapi.models.UpdateReservationDto
import com.deskshare.openapi.models.ViewReservationDto

class ReservationService : ReservationServiceInterface {
    private val manager: ReservationManager = ReservationManager()

    override suspend fun create(
        ctx: RequestContext<CreateCommandRequest>,
        requestDto: CreateReservationDto
    ): ViewReservationDto {
        ctx.request.requestModel = requestDto.toModel()
        manager.create(ctx)
        return ctx.request.responseModel.toDto()
    }

    override suspend fun update(
        ctx: RequestContext<UpdateCommandRequest>,
        requestDto: UpdateReservationDto
    ): ViewReservationDto {
        ctx.request.requestModel = requestDto.toModel()
        manager.update(ctx)
        return ctx.request.responseModel.toDto()
    }

    override suspend fun delete(
        ctx: RequestContext<DeleteCommandRequest>,
        reservationId: String
    ): ViewReservationDto {
        ctx.request.requestModelId = ReservationIdModel(reservationId)
        manager.delete(ctx)
        return ctx.request.responseModel.toDto()
    }

    override suspend fun findById(ctx: RequestContext<FindByIdQueryRequest>, reservationId: String): List<ViewReservationDto> {
        ctx.request.reservationId = ReservationIdModel(reservationId)
        manager.findById(ctx)
        return ctx.request.responseModels.map { it.toDto() }
    }

    override suspend fun findByFilter(ctx: RequestContext<FindByFilterQueryRequest>): List<ViewReservationDto> {
        manager.findByFilter(ctx)
        return ctx.request.responseModels.map { it.toDto() }
    }
}
