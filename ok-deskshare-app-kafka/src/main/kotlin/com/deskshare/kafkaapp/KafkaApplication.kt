package com.deskshare.kafkaapp

import com.deskshare.common.context.RequestContext
import com.deskshare.common.context.RequestInterface
import com.deskshare.common.context.command.CreateCommandRequest
import com.deskshare.common.context.command.DeleteCommandRequest
import com.deskshare.common.context.command.UpdateCommandRequest
import com.deskshare.common.models.error.CommonError
import com.deskshare.dto.mapping.rest.toErrorDto
import com.deskshare.kafka.KafkaConfig
import com.deskshare.kafka.dsl.kafka
import com.deskshare.openapi.models.CreateReservationDto
import com.deskshare.openapi.models.UpdateReservationDto
import com.deskshare.service.ReservationServiceInterface
import com.fasterxml.jackson.databind.ObjectMapper
import java.util.*

class KafkaApplication(
    private val kafkaConfig: KafkaConfig,
    private val service: ReservationServiceInterface
) {
    private val om = ObjectMapper()

    fun run() {
        kafka(kafkaConfig) {
            consumer("reservationCreateIn", "reservationCreate") {
                handle { message: String ->
                    val dto = om.readValue(message, CreateReservationDto::class.java)
                    handleRequest(topicOut = "reservationCreateOut", request = CreateCommandRequest()) {
                        service.create(ctx = this, requestDto = dto)
                    }
                }
            }

            consumer("reservationUpdateIn", "reservationUpdate") {
                handle { message: String ->
                    val dto = om.readValue(message, UpdateReservationDto::class.java)
                    handleRequest(topicOut = "reservationUpdateOut", request = UpdateCommandRequest()) {
                        service.update(ctx = this, requestDto = dto)
                    }
                }
            }

            consumer("reservationDeleteIn", "reservationDelete") {
                handle { message: String ->
                    handleRequest(topicOut = "reservationDeleteOut", request = DeleteCommandRequest()) {
                        service.delete(ctx = this, reservationId = message)
                    }
                }
            }
        }
    }

    private suspend inline fun <U : Any, R : RequestInterface> handleRequest(
        topicOut: String,
        request: R,
        block: suspend RequestContext<R>.() -> U
    ) {
        // todo read stub from kafka message header
        val ctx = RequestContext(
            requestId = UUID.randomUUID().toString(),
            request = request,
            stubCase = true
        )

        val responseDto = try {
            ctx.block()
        } catch (ex: Throwable) {
            ctx.finishedWithError(CommonError.fromThrowable(ex))
            ctx.toErrorDto()
        }

        kafka(kafkaConfig) {
            producer(topicOut) {
                val json = om.writeValueAsString(responseDto);
                send(UUID.randomUUID().toString(), json)
            }
        }
    }
}

