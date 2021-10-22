package com.deskshare.repo.test

import com.deskshare.common.models.*
import com.deskshare.common.repo.RepoInterface
import com.deskshare.common.repo.request.DbReservationIdRequest
import com.deskshare.common.repo.request.DbReservationRequest
import com.deskshare.stubs.ReservationStub
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

abstract class RepoReservationDeleteTest {
    abstract val repo: RepoInterface

    private val reservations: MutableList<ReservationModel> = mutableListOf()
    private var deleteId: ReservationIdModel = ReservationIdModel.NONE

    @BeforeTest
    fun initTest() {
        runBlocking {
            ReservationStub.getModels().forEach { reservations.add(repo.create(DbReservationRequest(it)).result!!) }
        }
        deleteId = reservations.first().id
    }

    @Test
    fun `Test delete reservation`() {
        val result = runBlocking { repo.delete(DbReservationIdRequest(reservationId = deleteId)) }

        assertEquals(emptyList(), result.errors)
        assertEquals(true, result.isSuccess)
        assertEquals(reservations.first(), result.result)
        assertEquals(emptyList(), result.errors)
    }

    @Test
    fun `Test delete reservation with not found`() {
        val result = runBlocking { repo.delete(DbReservationIdRequest(ReservationIdModel.fromRandom())) }

        assertTrue { result.errors.isNotEmpty() }
        assertEquals(false, result.isSuccess)
        assertEquals(null, result.result)
    }
}
