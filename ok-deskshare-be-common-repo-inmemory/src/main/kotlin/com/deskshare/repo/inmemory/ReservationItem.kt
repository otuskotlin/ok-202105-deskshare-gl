package com.deskshare.repo.inmemory

import com.deskshare.common.models.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class ReservationItem(
    val id: String? = null,
    val description: String? = null,
    val userId: String? = null,
    val workplaceId: String? = null,
    val from: String? = null,
    val until: String? = null,
    val status: String? = null,
    val createdAt: String? = null
) {
    constructor(model: ReservationModel): this(
        id = model.id.asString().takeIf { it.isNotEmpty() },
        description = model.description.takeIf { it.isNotBlank() },
        userId = model.userId.asString().takeIf { it.isNotEmpty() },
        workplaceId = model.workspaceId.asString().takeIf { it.isNotEmpty() },
        status = model.status.name,
        from = model.from.toString(),
        until = model.until.toString(),
        createdAt = model.createdAt.toString()
    )

    fun toModel() = ReservationModel(
        id = id?.let { ReservationIdModel(id) } ?: ReservationIdModel.NONE,
        description = description ?: "",
        userId = userId?.let { UserIdModel(userId) } ?: UserIdModel.NONE,
        workspaceId = workplaceId?.let { WorkspaceIdModel(workplaceId) } ?: WorkspaceIdModel.NONE,
        from = from?.let { LocalDateTime.parse(it, DateTimeFormatter.ISO_DATE_TIME) } ?: LocalDateTime.now(),
        until = until?.let { LocalDateTime.parse(it, DateTimeFormatter.ISO_DATE_TIME) } ?: LocalDateTime.now(),
        status = status?.let { ReservationStatus.valueOf(it) } ?: ReservationStatus.NONE
    )
}
