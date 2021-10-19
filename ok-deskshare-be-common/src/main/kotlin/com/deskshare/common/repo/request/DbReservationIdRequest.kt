package com.deskshare.common.repo

import com.deskshare.common.models.ReservationIdModel

data class DbReservationIdRequest(
    val reservationId: ReservationIdModel
) : DbRequestInterface
