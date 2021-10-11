import com.deskshare.kafka.Consumer
import com.deskshare.kafka.KafkaConfig
import com.deskshare.kafka.Producer
import com.deskshare.kafka.dsl.kafka
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.MockConsumer
import org.apache.kafka.clients.consumer.OffsetResetStrategy
import org.apache.kafka.clients.producer.MockProducer
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.common.serialization.StringSerializer
import java.util.*
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

    @Test
    fun `Test Kafka consumer dsl`() {
        val topicIn = "topic-in"

        val kafkaConsumerMock = MockConsumer<String, String>(OffsetResetStrategy.EARLIEST)
        kafkaConsumerMock.subscribe(listOf(topicIn))

        kafkaConsumerMock.schedulePollTask {
            kafkaConsumerMock.rebalance(Collections.singletonList(TopicPartition(topicIn, 0)))
            kafkaConsumerMock.addRecord(
                ConsumerRecord(
                    topicIn,
                    0,
                    0L,
                    "test-1",
                    "Hello"
                )
            )
        }

        val startOffsets = HashMap<TopicPartition, Long>()
        val tp = TopicPartition(topicIn, 0)
        startOffsets[tp] = 0L
        kafkaConsumerMock.updateBeginningOffsets(startOffsets)

        var results: MutableList<String> = mutableListOf()

        kafka(kafkaConfig = KafkaConfig()) {
            consumer(topicIn, "GroupID", kafkaConsumerMock) {
                handle { message: String ->
                    results.add(message)
                    assertEquals("Hello", results[0])
                    stop()
                }
            }
        }
    }
}
