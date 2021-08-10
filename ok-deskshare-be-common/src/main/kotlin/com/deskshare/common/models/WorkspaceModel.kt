package com.deskshare.common.models

data class WorkspaceModel(
    val id: WorkspaceIdModel = WorkspaceIdModel.NONE,
    val building: String = "",
    val floor: String = "",
    val room: String = "",
    val equipment: MutableSet<Equipment> = mutableSetOf(),
)
