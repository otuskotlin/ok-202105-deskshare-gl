package com.deskshare.kafkaapp

import com.deskshare.kafka.KafkaConfig
import com.deskshare.service.ReservationService

fun main() {
    val kafkaConfig = KafkaConfig()
    val service = ReservationService()
    val kafkaApplication = KafkaApplication(kafkaConfig, service)
    kafkaApplication.run()
}
