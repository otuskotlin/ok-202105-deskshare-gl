package com.deskshare.common.context.command

import com.deskshare.common.models.ReservationModel

data class CreateCommandRequest(
    val requestModel: ReservationModel,
    var responseModel: ReservationModel = ReservationModel()
) : CommandRequestInterface
