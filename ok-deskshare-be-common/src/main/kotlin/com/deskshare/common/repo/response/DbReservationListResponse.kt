package com.deskshare.common.repo

import com.deskshare.common.models.ReservationModel
import com.deskshare.common.models.error.CommonError

class DbReservationListResponse(
    override val result: List<ReservationModel>,
    override val isSuccess: Boolean,
    override val errors: List<CommonError> = emptyList(),
): DbResponseInterface<List<ReservationModel>> {
    constructor(reservations: List<ReservationModel>) : this(reservations, true)
    constructor(error: CommonError) : this(emptyList(), false, listOf(error))
}
