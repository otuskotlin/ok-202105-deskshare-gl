package com.deskshare.dto.mapping.rest

import com.deskshare.common.context.RequestContext
import com.deskshare.common.models.ReservationModel
import com.deskshare.common.models.ReservationStatus
import com.deskshare.common.models.error.ErrorInterface
import com.deskshare.openapi.models.ReservationStatusDto
import com.deskshare.openapi.models.ResponseErrorDto
import com.deskshare.openapi.models.ViewReservationDto
import java.time.format.DateTimeFormatter

fun ReservationModel.toDto() = ViewReservationDto(
    id = id.id,
    userId = userId.id,
    workspaceId = workspaceId.id,
    description = description,
    from = from.format(DateTimeFormatter.ISO_DATE_TIME),
    until = until.format(DateTimeFormatter.ISO_DATE_TIME),
    createdAt = createdAt.format(DateTimeFormatter.ISO_DATE_TIME),
    status = when (status) {
        ReservationStatus.PENDING -> ReservationStatusDto.PENDING
        ReservationStatus.CANCELED -> ReservationStatusDto.CANCELED
        ReservationStatus.CHECK_IN -> ReservationStatusDto.CHECK_IN
        ReservationStatus.CHECK_OUT -> ReservationStatusDto.CHECK_OUT
    }
)

fun ErrorInterface.toDto() = ResponseErrorDto(message = message, field = field)

fun RequestContext<*>.toErrorDto(): List<ResponseErrorDto> {
    return errors.map { it.toDto() }
}
