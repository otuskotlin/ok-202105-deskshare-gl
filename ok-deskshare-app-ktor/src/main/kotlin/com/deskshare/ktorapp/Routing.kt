package com.deskshare.ktorapp

import com.deskshare.ktorapp.controller.ReservationControllerInterface
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.health() = route("/health") {
    get {
        call.respond(status = HttpStatusCode.OK, message = "I am healthy :)")
    }
}

fun Route.reservations(reservationController: ReservationControllerInterface) = route("/reservations") {
    // commands
    post {
        reservationController.create(call);
    }
    delete("/{id}") {
        reservationController.delete(call)
    }
    put("/{id}") {
        reservationController.update(call)
    }

    // queries
    get {
        reservationController.findAll(call)
    }
    get("/{id}") {
        reservationController.findById(call)
    }
}
