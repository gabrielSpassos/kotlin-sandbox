package com.gabrielspassos.consumer

import com.gabrielspassos.event.ExchangeEvent
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import java.util.concurrent.atomic.AtomicReference

@Component
class ExchangeConsumer {

    private val logger: Log = LogFactory.getLog(javaClass)

    private val lastReceivedMessage = AtomicReference<ExchangeEvent?>()

    @KafkaListener(
        topics = ["exchange-topic"],
        groupId = "queue-testing-poc-kafka-group",
    )
    fun consumeUserEvent(exchangeEvent: ExchangeEvent): ExchangeEvent {
        logger.info("Received exchange event message: $exchangeEvent")
        lastReceivedMessage.set(exchangeEvent)
        return exchangeEvent
    }

    fun getLastReceivedMessage(): ExchangeEvent? {
        return lastReceivedMessage.get()
    }
}