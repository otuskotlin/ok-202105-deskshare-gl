package com.deskshare.kafka

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import java.time.Duration
import java.util.*

class Consumer(
    private val kafkaConfig: KafkaConfig,
    private val topic: String,
    private val groupId: String = UUID.randomUUID().toString(),
    private val kafkaConsumer: Consumer<String, String> = kafkaConsumer(kafkaConfig.kafkaHosts, topic, groupId)
) {
    @Volatile
    var process = true

    companion object {
        private fun kafkaConsumer(
            kafkaHosts: List<String>,
            topic: String,
            groupId: String
        ): KafkaConsumer<String, String> {
            val props = Properties().apply {
                put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaHosts)
                put(ConsumerConfig.GROUP_ID_CONFIG, groupId)
                put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java)
                put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java)
            }

            return KafkaConsumer<String, String>(props).apply { subscribe(listOf(topic)) }
        }
    }

    fun handle(block: (message: String) -> Unit) = runBlocking {
        process = true
        kafkaConsumer.use { kc ->
            while (process) {
                kc.poll(Duration.ofMillis(1000L))?.forEach {
                    withContext(Dispatchers.IO) { block(it?.value() ?: "???") }
                    // @todo commit with chunks
                    kafkaConsumer.commitSync()
                }
            }
        }
    }

    fun stop() {
        process = false
    }
}
