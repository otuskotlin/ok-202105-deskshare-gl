package com.deskshare.common.models

@JvmInline
value class UserIdModel(val id: String) {
    companion object {
        val NONE = UserIdModel("")
    }
}
