package com.deskshare.dto.mapping.rest

import com.deskshare.common.models.*
import com.deskshare.openapi.models.CreateReservationDto
import com.deskshare.openapi.models.UpdateReservationDto
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun CreateReservationDto.toModel() = ReservationModel(
    id = ReservationIdModel.UUID,
    description = description ?: "",
    userId = userId?.let { UserIdModel(userId.toString()) } ?: UserIdModel.NONE,
    workspaceId = workspaceId?.let { WorkspaceIdModel(workspaceId.toString()) } ?: WorkspaceIdModel.NONE,
    from = from?.let { LocalDateTime.parse(from, DateTimeFormatter.ISO_DATE_TIME) } ?: LocalDateTime.now(),
    until = until?.let { LocalDateTime.parse(until, DateTimeFormatter.ISO_DATE_TIME) } ?: LocalDateTime.now(),
    status = ReservationStatus.PENDING
)

fun UpdateReservationDto.toModel() = ReservationModel(
    id = ReservationIdModel(id ?: ""),
    description = description ?: "",
    userId = userId?.let { UserIdModel(userId.toString()) } ?: UserIdModel.NONE,
    workspaceId = workspaceId?.let { WorkspaceIdModel(workspaceId.toString()) } ?: WorkspaceIdModel.NONE,
    from = from?.let { LocalDateTime.parse(from, DateTimeFormatter.ISO_DATE_TIME) } ?: LocalDateTime.now(),
    until = until?.let { LocalDateTime.parse(until, DateTimeFormatter.ISO_DATE_TIME) } ?: LocalDateTime.now(),
    status = status?.let { ReservationStatus.valueOf(it.name) } ?: ReservationStatus.PENDING
)
