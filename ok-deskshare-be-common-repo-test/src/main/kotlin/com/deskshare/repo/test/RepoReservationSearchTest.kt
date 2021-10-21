package com.deskshare.repo.test

import com.deskshare.common.models.*
import com.deskshare.common.repo.RepoInterface
import com.deskshare.common.repo.request.DbReservationFilterRequest
import com.deskshare.common.repo.request.DbReservationIdRequest
import com.deskshare.common.repo.request.DbReservationRequest
import com.deskshare.stubs.ReservationStub
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

abstract class RepoReservationSearchTest {
    abstract val repo: RepoInterface

    private val reservations: MutableList<ReservationModel> = mutableListOf()

    @BeforeTest
    fun initTest() {
        runBlocking {
            listOf(
                ReservationModel(
                    id = ReservationIdModel(id = "123"),
                    userId = UserIdModel(id = "user123"),
                    workspaceId = WorkspaceIdModel(id = "1"),
                    from = LocalDateTime.now(),
                    until = LocalDateTime.now().plusHours(1L),
                    description = "my pending reservastion",
                    status = ReservationStatus.PENDING,
                    createdAt = LocalDateTime.now()
                ),
                ReservationModel(
                    id = ReservationIdModel(id = "456"),
                    userId = UserIdModel(id = "user456"),
                    workspaceId = WorkspaceIdModel(id = "1"),
                    from = LocalDateTime.now(),
                    until = LocalDateTime.now().plusHours(1L),
                    description = "my pending reservastion",
                    status = ReservationStatus.PENDING,
                    createdAt = LocalDateTime.now()
                )
            ).forEach { reservations.add(repo.create(DbReservationRequest(it)).result!!) }
        }
    }

    @Test
    fun `Test search by user id`() {
        val result = runBlocking { repo.search(DbReservationFilterRequest(userId = UserIdModel("user456"))) }
        val expected = listOf(reservations[1])

        assertEquals(emptyList(), result.errors)
        assertEquals(true, result.isSuccess)
        assertEquals(expected, result.result)
        assertEquals(emptyList(), result.errors)
    }

    @Test
    fun `Test search by workspace id`() {
        val result = runBlocking { repo.search(DbReservationFilterRequest(workspaceId = WorkspaceIdModel("1"))) }
        val expected = reservations

        assertEquals(emptyList(), result.errors)
        assertEquals(true, result.isSuccess)
        assertEquals(expected, result.result)
        assertEquals(emptyList(), result.errors)
    }
}
