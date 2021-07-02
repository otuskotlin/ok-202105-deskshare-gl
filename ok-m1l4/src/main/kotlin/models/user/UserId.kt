package models.user

import java.util.*

@JvmInline
value class UserId(val id: String) {
    companion object {
        val NONE = UserId("")
        fun random() = UserId(UUID.randomUUID().toString())
    }
}
