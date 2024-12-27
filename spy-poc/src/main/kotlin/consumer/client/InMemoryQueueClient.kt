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

    override fun consumeMessages(batchSize: Int): List<QueueMessage> {
        return stack.take(batchSize)
    }

    override fun deleteMessage(messageId: String): Boolean {
        val queueMessage = stack.firstOrNull { message -> message.id == messageId }
        if (null == queueMessage) {
            throw IllegalArgumentException("Message not found")
        }
        stack.remove(queueMessage)
        return true
    }

}