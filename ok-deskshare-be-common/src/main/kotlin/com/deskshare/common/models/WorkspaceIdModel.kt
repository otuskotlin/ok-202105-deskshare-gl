package com.deskshare.common.models

import java.util.*

@JvmInline
value class WorkspaceIdModel(val id: String) {
    constructor(id: UUID): this(id.toString())

    companion object {
        val NONE = WorkspaceIdModel("")
    }

    fun asString() = id
}
