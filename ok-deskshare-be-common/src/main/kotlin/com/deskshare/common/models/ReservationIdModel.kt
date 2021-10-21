package com.deskshare.common.models

import java.util.*
import java.util.UUID.*

@JvmInline
value class ReservationIdModel(val id: String) {
    constructor(id: UUID) : this(id.toString())

    companion object {
        fun fromRandom() = ReservationIdModel(randomUUID().toString())
        val NONE = ReservationIdModel("")
        val UUID = ReservationIdModel(randomUUID().toString())
    }

    fun asString() = id
}
