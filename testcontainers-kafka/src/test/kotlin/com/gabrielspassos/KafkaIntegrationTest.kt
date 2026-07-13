package com.gabrielspassos

import com.gabrielspassos.consumer.UserEventConsumer
import com.gabrielspassos.event.UserEvent
import com.gabrielspassos.producer.UserEventProducer
import org.awaitility.Awaitility.await
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import java.math.BigDecimal
import java.time.Duration
import java.time.LocalDateTime
import java.util.UUID
import java.util.concurrent.TimeUnit
import kotlin.test.assertEquals

@Import(TestcontainersConfiguration::class)
@SpringBootTest
class KafkaIntegrationTest {

    @Autowired
    private lateinit var producer: UserEventProducer

    @Autowired
    private lateinit var consumer: UserEventConsumer

    @Test
    fun shouldSendUserEventToKafka() {
        val userEventToProduce = UserEvent(
            id = UUID.randomUUID().toString(),
            usdRate = BigDecimal(1.5),
            rateDateTime = LocalDateTime.now().toString(),
        )
        val topic = "user-event-topic"

        val produceResult = producer.sendMessage(topic, userEventToProduce)

        assertTrue { produceResult }

        await()
            .atMost(10, TimeUnit.SECONDS)
            .pollInterval(Duration.ofMillis(500))
            .untilAsserted {
                val received = consumer.getLastReceivedMessage()
                assertEquals(userEventToProduce, received)
            }
    }

}