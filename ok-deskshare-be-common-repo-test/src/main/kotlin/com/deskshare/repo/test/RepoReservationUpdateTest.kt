package com.deskshare.repo.test

import com.deskshare.common.models.*
import com.deskshare.common.repo.RepoInterface
import com.deskshare.common.repo.request.DbReservationRequest
import com.deskshare.stubs.ReservationStub
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime
import java.util.*
import kotlin.test.*

abstract class RepoReservationUpdateTest {
    abstract val repo: RepoInterface

    private val reservations: MutableList<ReservationModel> = mutableListOf()
    private var updateId: ReservationIdModel = ReservationIdModel.NONE

    @BeforeTest
    fun initTest() {
        runBlocking {
            ReservationStub.getModels().forEach { reservations.add(repo.create(DbReservationRequest(it)).result!!) }
        }
        updateId = reservations.first().id
    }

    @Test
    fun `Test update reservation`() {
        val reservationUpdate = ReservationModel(
            id = updateId,
            userId = UserIdModel("123"),
            workspaceId = WorkspaceIdModel("123"),
            from = LocalDateTime.now(),
            until = LocalDateTime.now().plusHours(8),
            description = "description",
        )

        val result = runBlocking { repo.update(DbReservationRequest(reservationUpdate)) }

        assertEquals(emptyList(), result.errors)
        assertEquals(true, result.isSuccess)
        assertEquals(reservationUpdate, result.result)
        assertEquals(emptyList(), result.errors)
    }

    @Test
    fun `Test update reservation with not found`() {
        val reservationUpdate = ReservationModel(
            id = ReservationIdModel.fromRandom(),
            userId = UserIdModel("123"),
            workspaceId = WorkspaceIdModel("123"),
            from = LocalDateTime.now(),
            until = LocalDateTime.now().plusHours(8),
            description = "description",
        )

        val result = runBlocking { repo.update(DbReservationRequest(reservationUpdate)) }

        assertTrue { result.errors.isNotEmpty() }
        assertEquals(false, result.isSuccess)
        assertEquals(null, result.result)
    }
}
