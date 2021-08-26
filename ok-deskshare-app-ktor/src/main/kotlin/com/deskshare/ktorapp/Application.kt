package com.deskshare.ktorapp

import com.deskshare.ktorapp.controller.ReservationControllerInterface
import com.deskshare.ktorapp.service.init
import io.ktor.application.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    init()

    routing {
        health()

        val reservationController by inject<ReservationControllerInterface>()
        reservations(reservationController)
    }
}
