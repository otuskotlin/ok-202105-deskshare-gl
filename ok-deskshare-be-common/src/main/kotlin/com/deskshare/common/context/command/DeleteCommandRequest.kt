package com.deskshare.common.context.command

import com.deskshare.common.models.ReservationIdModel
import com.deskshare.common.models.ReservationModel

data class DeleteCommandRequest(
    val requestModelId: ReservationIdModel,
    var responseModel: ReservationModel = ReservationModel()
) : CommandRequestInterface
