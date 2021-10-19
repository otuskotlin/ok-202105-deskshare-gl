package com.deskshare.common.repo

import com.deskshare.common.models.ReservationModel
import com.deskshare.common.models.error.CommonError

data class DbReservationResponse(
    override val result: ReservationModel?,
    override val isSuccess: Boolean,
    override val errors: List<CommonError> = emptyList(),
) : DbResponseInterface<ReservationModel?> {
    constructor(reservationModel: ReservationModel) : this(reservationModel, true)
    constructor(error: CommonError) : this(null, false, listOf(error))
}
