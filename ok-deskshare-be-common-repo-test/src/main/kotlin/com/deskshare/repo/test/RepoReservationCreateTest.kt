package com.deskshare.repo.test

import com.deskshare.common.models.*
import com.deskshare.common.repo.RepoInterface
import com.deskshare.common.repo.request.DbReservationRequest
import com.deskshare.stubs.ReservationStub
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime
import kotlin.test.Test
import kotlin.test.assertEquals

abstract class RepoReservationCreateTest {
    abstract val repo: RepoInterface

    @Test
    fun `Test create reservation`() {
        val reservation = ReservationModel(
            userId = UserIdModel("123"),
            workspaceId = WorkspaceIdModel("123"),
            from = LocalDateTime.now(),
            until = LocalDateTime.now().plusHours(8),
            description = "description",
        )
        val result = runBlocking { repo.create(DbReservationRequest(reservation)) }
        val expected = reservation.copy(id = result?.result?.id ?: ReservationIdModel.NONE)

        assertEquals(true, result.isSuccess)
        assertEquals(expected, result.result)
        assertEquals(emptyList(), result.errors)
    }
}
