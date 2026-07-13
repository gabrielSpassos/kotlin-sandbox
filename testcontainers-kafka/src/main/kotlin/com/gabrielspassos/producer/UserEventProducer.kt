package com.gabrielspassos.producer

import com.gabrielspassos.event.UserEvent
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class UserEventProducer(private val kafkaTemplate: KafkaTemplate<String, UserEvent>) {

    fun sendMessage(topic: String, userEvent: UserEvent): Boolean {
        kafkaTemplate.send(topic, userEvent)
        return true
    }

}