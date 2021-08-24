package com.deskshare.common.context.command

import com.deskshare.common.models.ReservationIdModel

data class DeleteCommand(val reservationId: ReservationIdModel): CommandInterface
