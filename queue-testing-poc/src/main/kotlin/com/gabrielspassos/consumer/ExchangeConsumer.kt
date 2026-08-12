package com.gabrielspassos.consumer

import com.gabrielspassos.event.ExchangeEvent
import com.gabrielspassos.service.InductionService
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Component
import java.util.concurrent.atomic.AtomicReference

@Component
class ExchangeConsumer(private val inductionService: InductionService) {

    private val logger: Log = LogFactory.getLog(javaClass)
    private val lastReceivedMessage = AtomicReference<ExchangeEvent?>()

    @KafkaListener(
        topics = ["exchange-topic"],
        groupId = "queue-testing-poc-kafka-group",
    )
    fun consumeExchangeEvent(
        exchangeEvent: ExchangeEvent,
        @Header(name = "test-induction-id", required = false) testInductionId: ByteArray?
    ): ExchangeEvent {
        val testInductionIdValue = testInductionId?.let { String(it) }
        logger.info("Received exchange event message: $exchangeEvent, testInductionId=$testInductionIdValue")
        lastReceivedMessage.set(exchangeEvent)
        inductionService.processExchangeConsumerEvent(exchangeEvent, testInductionIdValue)
        return exchangeEvent
    }

    fun getLastReceivedMessage(): ExchangeEvent? {
        return lastReceivedMessage.get()
    }
}