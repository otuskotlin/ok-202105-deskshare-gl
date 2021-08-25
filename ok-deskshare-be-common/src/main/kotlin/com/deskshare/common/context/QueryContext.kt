package com.deskshare.common.context

import com.deskshare.common.context.query.FindByFilterQuery
import com.deskshare.common.context.query.FindByIdQuery
import com.deskshare.common.context.query.QueryInterface
import com.deskshare.common.models.ReservationIdModel
import com.deskshare.common.models.ReservationModel

class QueryContext<T : QueryInterface>(
    val query: T,
    requestId: String = "",
    requestLocale: LocaleModel = LocaleModel.EN
) : Context(requestId, requestLocale) {
    var responseReservations: MutableList<ReservationModel> = mutableListOf()

    companion object {
        fun forFindByFilter() = QueryContext(FindByFilterQuery())
        fun forFindById(id: String) = QueryContext(FindByIdQuery(ReservationIdModel(id)))
    }
}
