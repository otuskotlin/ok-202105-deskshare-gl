package com.deskshare.common.context

import com.deskshare.common.context.query.FindByFilterQuery
import com.deskshare.common.context.query.FindByIdQuery
import com.deskshare.common.context.query.QueryInterface
import com.deskshare.common.models.ReservationIdModel
import com.deskshare.common.models.ReservationModel

class QueryRequestContext<T : QueryInterface>(val query: T, requestId: String = "") : RequestContext(requestId) {
    companion object {
        fun forFindByFilter(requestId: String = "") =
            QueryRequestContext(FindByFilterQuery(), requestId)

        fun forFindById(id: String, requestId: String = "") =
            QueryRequestContext(FindByIdQuery(ReservationIdModel(id)), requestId)
    }
}
