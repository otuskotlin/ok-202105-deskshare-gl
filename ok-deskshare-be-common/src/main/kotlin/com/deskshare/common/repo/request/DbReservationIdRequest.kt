package com.deskshare.common.repo.request

import com.deskshare.common.models.ReservationIdModel
import com.deskshare.common.repo.request.DbRequestInterface

data class DbReservationIdRequest(
    val reservationId: ReservationIdModel
) : DbRequestInterface
