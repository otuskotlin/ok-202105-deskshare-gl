package com.deskshare.common.models

import java.util.*

@JvmInline
value class UserIdModel(val id: String) {
    constructor(id: UUID): this(id.toString())

    companion object {
        val NONE = UserIdModel("")
    }

    fun asString() = id
}
