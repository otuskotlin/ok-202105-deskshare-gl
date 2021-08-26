package com.deskshare.ktorapp.controller

import io.ktor.application.*

interface ReservationControllerInterface {
    suspend fun create(call: ApplicationCall)

    suspend fun update(call: ApplicationCall)

    suspend fun delete(call: ApplicationCall)

    suspend fun findById(call: ApplicationCall)

    suspend fun findAll(call: ApplicationCall)
}
