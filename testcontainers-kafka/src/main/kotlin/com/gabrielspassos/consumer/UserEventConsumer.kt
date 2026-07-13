package com.gabrielspassos.consumer

import com.gabrielspassos.event.UserEvent
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import java.util.concurrent.atomic.AtomicReference

@Component
class UserEventConsumer {

    private val log: Log = LogFactory.getLog(javaClass)

    private val lastReceivedMessage = AtomicReference<UserEvent?>()

    @KafkaListener(topics = ["user-event-topic"], groupId = "testcontainers-kafka-group")
    fun consumeUserEvent(userEvent: UserEvent): UserEvent {
        log.info("Received user event message: $userEvent")
        lastReceivedMessage.set(userEvent)
        return userEvent
    }

    fun getLastReceivedMessage(): UserEvent? {
        return lastReceivedMessage.get()
    }
}