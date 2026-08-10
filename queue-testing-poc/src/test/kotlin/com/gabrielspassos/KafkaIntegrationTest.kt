package com.gabrielspassos

import com.gabrielspassos.consumer.ExchangeConsumer
import com.gabrielspassos.event.ExchangeEvent
import com.gabrielspassos.producer.ExchangeProducer
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
    private lateinit var producer: ExchangeProducer

    @Autowired
    private lateinit var consumer: ExchangeConsumer

    @Test
    fun shouldSendUserEventToKafka() {
        val exchangeToProduce = ExchangeEvent(
            id = UUID.randomUUID().toString(),
            usdToBrlRate = BigDecimal(1.5),
            rateDateTime = LocalDateTime.now().toString(),
        )

        val produceResult = producer.sendMessage(exchangeToProduce)

        assertTrue { produceResult }

        await()
            .atMost(10, TimeUnit.SECONDS)
            .pollInterval(Duration.ofMillis(500))
            .untilAsserted {
                val received = consumer.getLastReceivedMessage()
                assertEquals(exchangeToProduce, received)
            }
    }

}