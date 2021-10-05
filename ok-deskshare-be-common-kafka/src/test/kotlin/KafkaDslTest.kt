import com.deskshare.kafka.KafkaConfig
import com.deskshare.kafka.Producer
import com.deskshare.kafka.dsl.kafka
import org.apache.kafka.clients.producer.MockProducer
import org.apache.kafka.common.serialization.StringSerializer
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class KafkaDslTest {
    @Test
    fun `Test Kafka producer dsl`() {
        val kafkaConfig = KafkaConfig()
        val kafkaProducerMock = MockProducer<String, String>(true, StringSerializer(), StringSerializer())
        val topicIn = "topic-in"

        kafka(kafkaConfig) {
            producer(topicIn, kafkaProducerMock) {
                send("123", "Hello")
            }
        }

        val result = kafkaProducerMock.history().get(0).value()
        assertEquals("Hello", result)
    }
}
