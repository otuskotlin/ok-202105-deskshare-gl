package com.deskshare.common.models

import java.time.LocalDateTime

data class ReservationModel(
    val id: ReservationIdModel = ReservationIdModel.NONE,
    val userId: UserIdModel = UserIdModel.NONE,
    val workspaceId: String = "",
    val from: LocalDateTime = LocalDateTime.now(),
    val until: LocalDateTime = LocalDateTime.now(),
    val description: String = "",
    val status: ReservationStatus = ReservationStatus.PENDING,
    val createdAt: LocalDateTime = LocalDateTime.now()
)
