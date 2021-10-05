package com.deskshare.kafkaapp

import com.deskshare.kafka.KafkaConfig
import com.deskshare.kafka.dsl.kafka

fun main() {
    val kafkaConfig = KafkaConfig()

    kafka(kafkaConfig) {
        consumer("topic1", "23") {
            Runtime.getRuntime().addShutdownHook(Thread(Runnable { stop() }))

            handle { message: String ->
                println("_____")
                println(message)
                println("_____")
            }
        }
    }
}
