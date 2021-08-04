package com.deskshare.common.models

@JvmInline
value class WorkspaceIdModel(val id: String) {
    companion object {
        val NONE = WorkspaceIdModel("")
    }
}
