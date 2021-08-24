package com.deskshare.common.context.command

import com.deskshare.common.models.ReservationModel

data class UpdateCommand(val oldModel: ReservationModel): CommandInterface
