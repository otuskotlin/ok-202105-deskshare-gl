package com.deskshare.common.context.command

import com.deskshare.common.models.ReservationModel

data class UpdateCommandRequest(
    val requestModel: ReservationModel,
    var responseModel: ReservationModel = ReservationModel()
) : CommandRequestInterface
