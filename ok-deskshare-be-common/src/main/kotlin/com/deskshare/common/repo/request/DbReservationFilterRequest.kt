package com.deskshare.common.repo.request

import com.deskshare.common.models.ReservationIdModel

data class DbReservationFilterRequest(
    val searchString: String = "",
    val reservationId: ReservationIdModel = ReservationIdModel.NONE
) : DbRequestInterface {
    companion object {
        val NONE = DbReservationFilterRequest()
    }
}
