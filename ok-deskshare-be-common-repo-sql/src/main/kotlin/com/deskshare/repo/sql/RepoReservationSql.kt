package com.deskshare.repo.sql

import com.deskshare.common.repo.RepoInterface
import com.deskshare.common.repo.request.DbReservationFilterRequest
import com.deskshare.common.repo.request.DbReservationIdRequest
import com.deskshare.common.repo.request.DbReservationRequest
import com.deskshare.common.repo.response.DbReservationListResponse
import com.deskshare.common.repo.response.DbReservationResponse

class RepoReservationSql: RepoInterface {
    override suspend fun create(req: DbReservationRequest): DbReservationResponse {
        TODO("Not yet implemented")
    }

    override suspend fun update(req: DbReservationRequest): DbReservationResponse {
        TODO("Not yet implemented")
    }

    override suspend fun delete(req: DbReservationIdRequest): DbReservationResponse {
        TODO("Not yet implemented")
    }

    override suspend fun read(req: DbReservationIdRequest): DbReservationResponse {
        TODO("Not yet implemented")
    }

    override suspend fun search(req: DbReservationFilterRequest): DbReservationListResponse {
        TODO("Not yet implemented")
    }
}
