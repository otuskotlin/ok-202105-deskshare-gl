package com.deskshare.kafka

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import java.util.*

class Producer(
    private val kafkaConfig: KafkaConfig,
    private val topic: String,
    private val kafkaProducer: Producer<String, String> = kafkaProducer(kafkaConfig.kafkaHosts)
) {
    companion object {
        private fun kafkaProducer(kafkaHosts: List<String>): KafkaProducer<String, String> {
            val config = Properties().apply {
                put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaHosts)
                put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java)
                put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java)
            }
            return KafkaProducer(config)
        }
    }

    fun send(key: String = UUID.randomUUID().toString(), message: String) {
        kafkaProducer.send(ProducerRecord(topic, key, message))
    }

    fun flush() = kafkaProducer.flush()
}
