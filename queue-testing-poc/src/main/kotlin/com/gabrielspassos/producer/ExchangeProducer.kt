package com.gabrielspassos.producer

import com.gabrielspassos.event.ExchangeEvent
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class ExchangeProducer(private val kafkaTemplate: KafkaTemplate<String, ExchangeEvent>) {

    fun sendMessage(exchangeEvent: ExchangeEvent): Boolean {
        kafkaTemplate.send("exchange-topic", exchangeEvent)
        return true
    }

}