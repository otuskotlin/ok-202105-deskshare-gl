package com.deskshare.common.context.query

import com.deskshare.common.models.ReservationModel

class FindByFilterQueryRequest(
    val query: String = "",
    var responseModels: MutableList<ReservationModel> = mutableListOf()
) : QueryRequestInterface

