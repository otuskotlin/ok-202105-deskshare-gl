package com.deskshare.repo.inmemory

import com.deskshare.common.models.ReservationIdModel
import com.deskshare.common.models.ReservationModel
import com.deskshare.common.models.error.CommonError
import com.deskshare.common.repo.RepoInterface
import com.deskshare.common.repo.request.DbReservationFilterRequest
import com.deskshare.common.repo.request.DbReservationIdRequest
import com.deskshare.common.repo.request.DbReservationRequest
import com.deskshare.common.repo.response.DbReservationListResponse
import com.deskshare.common.repo.response.DbReservationResponse
import org.ehcache.Cache
import org.ehcache.CacheManager
import org.ehcache.config.builders.CacheConfigurationBuilder
import org.ehcache.config.builders.CacheManagerBuilder
import org.ehcache.config.builders.ExpiryPolicyBuilder
import org.ehcache.config.builders.ResourcePoolsBuilder
import java.time.Duration

class RepoReservationInMemory(
    private val ttl: Duration = Duration.ofMinutes(1)
) : RepoInterface {

    private val cache: Cache<String, ReservationItem> = let {
        val cacheManager: CacheManager = CacheManagerBuilder
            .newCacheManagerBuilder()
            .build(true)

        cacheManager.createCache(
            "deskshare-cache",
            CacheConfigurationBuilder
                .newCacheConfigurationBuilder(
                    String::class.java,
                    ReservationItem::class.java,
                    ResourcePoolsBuilder.heap(100)
                )
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(ttl))
                .build()
        )
    }

    private suspend fun save(model: ReservationModel): DbReservationResponse {
        if (model.id == null) {
            return DbReservationResponse(
                CommonError.fromRuntime("Id must not be null or blank")
            )
        }
        cache.put(model.id.toString(), ReservationItem(model))
        return DbReservationResponse(model)
    }

    override suspend fun create(req: DbReservationRequest): DbReservationResponse =
        save(req.reservation.copy(id = ReservationIdModel.UUID))


    override suspend fun update(req: DbReservationRequest): DbReservationResponse {
        if (req.reservation.id == ReservationIdModel.NONE) {
            return DbReservationResponse(CommonError.fromRuntime("Id must not be null or blank"))
        }

        val cacheKey = req.reservation.id.toString()
        return if (cache.containsKey(cacheKey)) {
            save(req.reservation)
            DbReservationResponse(req.reservation)
        } else {
            DbReservationResponse(CommonError.fromRuntime("Id $cacheKey not found"))
        }
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
