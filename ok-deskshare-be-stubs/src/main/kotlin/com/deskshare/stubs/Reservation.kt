package com.deskshare.stubs

import com.deskshare.common.models.*
import java.time.LocalDateTime

object Reservation {
    private val reservationPending = ReservationModel(
        id = ReservationIdModel(id = "123"),
        userId = UserIdModel(id = "user"),
        workspaceId = WorkspaceIdModel(id = "1"),
        from = LocalDateTime.now(),
        until = LocalDateTime.now(),
        description = "my pending reservastion",
        status = ReservationStatus.PENDING,
        createdAt = LocalDateTime.now()
    )

    private val reservationCheckedIn = ReservationModel(
        id = ReservationIdModel(id = "124"),
        userId = UserIdModel(id = "user"),
        workspaceId = WorkspaceIdModel(id = "1"),
        from = LocalDateTime.now(),
        until = LocalDateTime.now(),
        description = "my checked in reservastion",
        status = ReservationStatus.CHECK_IN,
        createdAt = LocalDateTime.now()
    )

    private val reservationCheckedOut = ReservationModel(
        id = ReservationIdModel(id = "125"),
        userId = UserIdModel(id = "user"),
        workspaceId = WorkspaceIdModel(id = "1"),
        from = LocalDateTime.now(),
        until = LocalDateTime.now(),
        description = "my checked out reservastion",
        status = ReservationStatus.CHECK_OUT,
        createdAt = LocalDateTime.now()
    )

    private val reservationCanceled = ReservationModel(
        id = ReservationIdModel(id = "125"),
        userId = UserIdModel(id = "user"),
        workspaceId = WorkspaceIdModel(id = "1"),
        from = LocalDateTime.now(),
        until = LocalDateTime.now(),
        description = "my checked out reservastion",
        status = ReservationStatus.CANCELED,
        createdAt = LocalDateTime.now()
    )

    fun getModel() = reservationPending

    fun getPendingModel() = getModel()

    fun getCanceledModel() = reservationCanceled

    fun isCorrectId(id: String) = id == "123"

    fun getModels() = listOf(
        reservationPending,
        reservationCheckedIn,
        reservationCheckedOut
    )
}
