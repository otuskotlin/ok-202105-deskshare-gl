package com.deskshare.dto.mapping.rest

import com.deskshare.common.models.ReservationModel
import com.deskshare.openapi.models.ReservationStatusDto
import com.deskshare.openapi.models.ViewReservationDto
import java.time.format.DateTimeFormatter

fun ReservationModel.toDto() = ViewReservationDto(
    id = id.id,
    userId = userId.id,
    workspaceId = workspaceId.id,
    description = description,
    from = from.format(DateTimeFormatter.ISO_DATE_TIME),
    until = from.format(DateTimeFormatter.ISO_DATE_TIME),
    status = ReservationStatusDto.valueOf(status.name),
    createdAt = createdAt.format(DateTimeFormatter.ISO_DATE_TIME)
)
