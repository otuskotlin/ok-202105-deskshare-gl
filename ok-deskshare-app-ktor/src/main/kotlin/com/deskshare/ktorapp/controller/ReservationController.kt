package com.deskshare.ktorapp.controller

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.command.CreateCommandRequest
import com.deskshare.common.context.command.DeleteCommandRequest
import com.deskshare.common.context.command.UpdateCommandRequest
import com.deskshare.common.context.query.FindByFilterQueryRequest
import com.deskshare.common.context.query.FindByIdQueryRequest
import com.deskshare.ktorapp.service.respond
import com.deskshare.service.ReservationServiceInterface
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.request.*

class ReservationController(private val service: ReservationServiceInterface) : ReservationControllerInterface {
    // @todo read from header
    private val stubCase: Boolean = true

    override suspend fun create(call: ApplicationCall) {
        with(call) {
            val commandCtx = RequestContext(
                requestId = callId.toString(),
                request = CreateCommandRequest(),
                stubCase = stubCase
            )

            respond(
                commandCtx, try {
                    service.create(commandCtx, receive())
                } catch (e: Throwable) {
                    service.handleError(commandCtx, e)
                }
            )
        }
    }

    override suspend fun update(call: ApplicationCall) {
        with(call) {
            val commandCtx = RequestContext(
                requestId = callId.toString(),
                request = UpdateCommandRequest(),
                stubCase = stubCase
            )

            respond(
                commandCtx, try {
                    service.update(commandCtx, receive())
                } catch (e: Throwable) {
                    service.handleError(commandCtx, e)
                }
            )
        }
    }

    override suspend fun delete(call: ApplicationCall) {
        with(call) {
            val commandCtx = RequestContext(
                requestId = call.callId.toString(),
                request = DeleteCommandRequest(),
                stubCase = stubCase
            )

            respond(
                commandCtx, try {
                    service.delete(commandCtx, parameters["id"].toString())
                } catch (e: Throwable) {
                    service.handleError(commandCtx, e)
                }
            )
        }
    }

    override suspend fun findById(call: ApplicationCall) {
        with(call) {
            val queryCtx = RequestContext(
                requestId = callId.toString(),
                request = FindByIdQueryRequest(),
                stubCase = stubCase
            )

            respond(
                queryCtx, try {
                    service.findById(queryCtx, parameters["id"].toString())
                } catch (e: Throwable) {
                    service.handleError(queryCtx, e)
                }
            )
        }
    }

    override suspend fun findAll(call: ApplicationCall) {
        // todo implement filter, paging etc.
        with(call) {
            val queryCtx = RequestContext(
                requestId = callId.toString(),
                request = FindByFilterQueryRequest(),
                stubCase = stubCase
            )

            respond(
                queryCtx, try {
                    service.findByFilter(queryCtx)
                } catch (e: Throwable) {
                    service.handleError(queryCtx, e)
                }
            )
        }
    }
}
