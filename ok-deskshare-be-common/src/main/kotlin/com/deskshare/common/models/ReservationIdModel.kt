package com.deskshare.common.models

import java.util.UUID.*

@JvmInline
value class ReservationIdModel(val id: String) {
    companion object {
        val NONE = ReservationIdModel("")
        val UUID = ReservationIdModel(randomUUID().toString())
    }

    fun asString() = id
}
