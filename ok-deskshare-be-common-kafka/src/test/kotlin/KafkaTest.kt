import com.deskshare.kafka.Consumer
import com.deskshare.kafka.KafkaConfig
import com.deskshare.kafka.Producer
import com.fasterxml.jackson.databind.ObjectMapper
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


class KafkaTest {

    @Test
    fun `Test Kafka producer with mock `() {
        val kafkaProducerMock = MockProducer<String, String>(true, StringSerializer(), StringSerializer())

        val topicIn = "topic-in"
        val json = om.writeValueAsString(TestContainer(name = "Grischa", age = 10));

        val producer = Producer(KafkaConfig(), topicIn, kafkaProducerMock)
        producer.send("123", json)

        assertTrue(kafkaProducerMock.history().size == 1);

        val result = kafkaProducerMock.history().get(0).value().fromJson<TestContainer>()
        assertEquals(10, result.age)
        assertEquals("Grischa", result.name)
    }

    @Test
    fun `Test Kafka consumer with mock `() {
        val topicIn = "topic-in"

        val kafkaConsumerMock = MockConsumer<String, String>(OffsetResetStrategy.EARLIEST)
        kafkaConsumerMock.subscribe(listOf(topicIn))

        val consumer = Consumer(
            kafkaConfig = KafkaConfig(),
            topic = topicIn,
            groupId = UUID.randomUUID().toString(),
            kafkaConsumer = kafkaConsumerMock
        )

        kafkaConsumerMock.schedulePollTask {
            kafkaConsumerMock.rebalance(Collections.singletonList(TopicPartition(topicIn, 0)))
            kafkaConsumerMock.addRecord(
                ConsumerRecord(
                    topicIn,
                    0,
                    0L,
                    "test-1",
                    om.writeValueAsString(TestContainer(name = "Grischa", age = 10))
                )
            )
            consumer.stop()
        }

        val startOffsets = HashMap<TopicPartition, Long>()
        val tp = TopicPartition(topicIn, 0)
        startOffsets[tp] = 0L
        kafkaConsumerMock.updateBeginningOffsets(startOffsets)

        var results: MutableList<TestContainer> = mutableListOf()
        consumer.handle { message:String ->
            results.add(message.fromJson())

            assertEquals(1, results.size)
            assertEquals(10, results[0].age)
            assertEquals("Grischa", results[0].name)
        }

    }
}

data class TestContainer(val name: String = "", val age: Int = 0)

private val om = ObjectMapper()
private inline fun <reified T : TestContainer> String.fromJson() = om.readValue<T>(this, T::class.java)
