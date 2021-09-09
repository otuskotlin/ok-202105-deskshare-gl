package com.deskshare.common.context.command

import com.deskshare.common.models.ReservationModel

data class UpdateCommandRequest(
    var requestModel: ReservationModel = ReservationModel(),
    var responseModel: ReservationModel = ReservationModel()
) : CommandRequestInterface
