package com.deskshare.common.context.command

import com.deskshare.common.models.ReservationModel

data class CreateCommand(val newModel: ReservationModel): CommandInterface
