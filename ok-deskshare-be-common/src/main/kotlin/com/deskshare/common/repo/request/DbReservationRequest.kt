package com.deskshare.common.repo

import com.deskshare.common.models.ReservationModel

data class DbReservationRequest(
    val reservation: ReservationModel
) : DbRequestInterface
