package com.deskshare.common.repo.request

import com.deskshare.common.models.UserIdModel
import com.deskshare.common.models.WorkspaceIdModel

data class DbReservationFilterRequest(
    val workspaceId: WorkspaceIdModel = WorkspaceIdModel.NONE,
    val userId: UserIdModel = UserIdModel.NONE
) : DbRequestInterface {
    companion object {
        val NONE = DbReservationFilterRequest()
    }
}
