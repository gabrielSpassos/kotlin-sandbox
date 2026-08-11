package com.gabrielspassos.producer

import com.gabrielspassos.event.ExchangeEvent
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component


@Component
class ExchangeProducer(private val kafkaTemplate: KafkaTemplate<String, ExchangeEvent>) {

    fun sendMessage(exchangeEvent: ExchangeEvent, testInductionId: String? = null): Boolean {
        val record = ProducerRecord<String, ExchangeEvent>("exchange-topic", null, exchangeEvent)

        if (testInductionId?.isNotBlank() == true) {
            record.headers().add("test-induction-id", testInductionId.toByteArray())
        }

        kafkaTemplate.send(record)
        return true
    }

}