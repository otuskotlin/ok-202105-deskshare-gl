package com.deskshare.kafka

data class KafkaConfig(val kafkaHosts: List<String> = KAFKA_HOSTS) {
    companion object {
        const val KAFKA_HOST_VAR = "KAFKA_HOSTS"

        val KAFKA_HOSTS by lazy { (System.getenv(KAFKA_HOST_VAR) ?: "localhost:9094").split("\\s*[,;]\\s*") }
    }
}
