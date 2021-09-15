package com.deskshare.common.context.command

import com.deskshare.common.models.ReservationIdModel
import com.deskshare.common.models.ReservationModel

data class DeleteCommandRequest(
    var requestModelId: ReservationIdModel = ReservationIdModel.NONE,
    var responseModel: ReservationModel = ReservationModel()
) : CommandRequestInterface
