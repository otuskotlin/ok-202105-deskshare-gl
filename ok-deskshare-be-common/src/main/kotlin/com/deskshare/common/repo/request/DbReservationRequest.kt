package com.deskshare.common.repo.request

import com.deskshare.common.models.ReservationModel
import com.deskshare.common.repo.request.DbRequestInterface

data class DbReservationRequest(
    val reservation: ReservationModel
) : DbRequestInterface
