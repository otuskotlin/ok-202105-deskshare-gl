package com.deskshare.kafka.dsl

import com.deskshare.kafka.Consumer
import com.deskshare.kafka.KafkaConfig
import com.deskshare.kafka.Producer
import org.apache.kafka.clients.consumer.Consumer as KafkaConsumer
import org.apache.kafka.clients.producer.Producer as KafkaProducer

class KafkaDsl(val kafkaConfig: KafkaConfig) {
    fun consumer(topic: String, groupId: String, block: Consumer.() -> Unit) =
        Consumer(kafkaConfig, topic, groupId).block()

    fun consumer(
        topic: String,
        groupId: String,
        kafkaConsumer: KafkaConsumer<String, String>,
        block: Consumer.() -> Unit
    ) =
        Consumer(kafkaConfig, topic, groupId, kafkaConsumer).block()

    fun consumer(topic: String, block: Consumer.() -> Unit) =
        Consumer(kafkaConfig, topic).block()

    fun producer(topic: String, block: Producer.() -> Unit) =
        Producer(kafkaConfig, topic).block()

    fun producer(topic: String, kafkaProducer: KafkaProducer<String, String>, block: Producer.() -> Unit) =
        Producer(kafkaConfig, topic, kafkaProducer).block()
}

fun kafka(kafkaConfig: KafkaConfig, block: KafkaDsl.() -> Unit) = KafkaDsl(kafkaConfig).block()
