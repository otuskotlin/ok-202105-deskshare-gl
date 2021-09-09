package com.deskshare.common.context.query

import com.deskshare.common.models.ReservationIdModel
import com.deskshare.common.models.ReservationModel

class FindByIdQueryRequest(
    var reservationId: ReservationIdModel = ReservationIdModel.NONE,
    var responseModels: MutableList<ReservationModel> = mutableListOf()
) : QueryRequestInterface
