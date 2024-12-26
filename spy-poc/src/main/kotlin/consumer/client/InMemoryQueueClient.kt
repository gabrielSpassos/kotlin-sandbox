package com.gabrielspassos.consumer.client

import com.gabrielspassos.consumer.model.QueueMessage
import java.util.Optional

class InMemoryQueueClient: QueueService {

    private val stack = mutableListOf<QueueMessage>()

    override fun addMessage(queueMessage: QueueMessage): Boolean {
        stack.addFirst(queueMessage)
        return true
    }

    override fun consumeMessage(): Optional<QueueMessage> {
        return Optional.ofNullable(stack.getOrNull(0))
    }

    override fun deleteMessage(messageId: String): Boolean {
        val queueMessage = stack.first { message -> message.id == messageId }
        stack.remove(queueMessage)
        return true
    }

}