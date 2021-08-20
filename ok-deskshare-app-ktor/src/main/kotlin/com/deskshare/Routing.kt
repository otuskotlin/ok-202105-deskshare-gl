package com.deskshare

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.health() = route("/health") {
    get {
        call.respond(status = HttpStatusCode.OK, message = "I am healthy")
    }
}

fun Route.reservations() = route("/reservations") {
    get {
        call.respondText("get alle")
    }

    get("/{id}") {
        //call.respondText(call.parameters["id"].toString())
        call.respondText("get one")
    }

    delete("/{id}") {
        //call.respondText(call.parameters["id"].toString())
        call.respondText("delete one")
    }

    put("/{id}") {
        //call.respondText(call.parameters["id"].toString())
        call.respondText("edit one")
    }

    post {
        call.respondText("new")
    }
}
