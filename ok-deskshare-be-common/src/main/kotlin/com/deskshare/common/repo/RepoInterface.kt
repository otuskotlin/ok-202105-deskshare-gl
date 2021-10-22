package com.deskshare.common.repo

import com.deskshare.common.repo.request.DbReservationFilterRequest
import com.deskshare.common.repo.request.DbReservationIdRequest
import com.deskshare.common.repo.request.DbReservationRequest
import com.deskshare.common.repo.response.DbReservationListResponse
import com.deskshare.common.repo.response.DbReservationResponse

interface RepoInterface {
    suspend fun create(req: DbReservationRequest): DbReservationResponse
    suspend fun update(req: DbReservationRequest): DbReservationResponse
    suspend fun delete(req: DbReservationIdRequest): DbReservationResponse
    suspend fun read(req: DbReservationIdRequest): DbReservationResponse
    suspend fun search(req: DbReservationFilterRequest): DbReservationListResponse

    object NONE: RepoInterface {
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
}
