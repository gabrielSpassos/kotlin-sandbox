package com.gabrielspassos.consumer.client

import com.gabrielspassos.consumer.model.QueueMessage
import java.util.Optional

class InMemoryQueueClient: QueueService {

    private val stackMap = mutableMapOf<String, MutableList<QueueMessage>>()

    override fun addMessage(queueName: String, queueMessage: QueueMessage): Boolean {
        val stack = getOrCreateStackByName(queueName)
        stack.addFirst(queueMessage)
        return true
    }

    override fun consumeMessage(queueName: String,): Optional<QueueMessage> {
        val stack = getOrCreateStackByName(queueName)
        return Optional.ofNullable(stack.getOrNull(0))
    }

    override fun consumeMessages(queueName: String, batchSize: Int): List<QueueMessage> {
        val stack = getOrCreateStackByName(queueName)
        return stack.take(batchSize)
    }

    override fun deleteMessage(queueName: String, messageId: String): Boolean {
        val stack = getOrCreateStackByName(queueName)
        val queueMessage = stack.firstOrNull { message -> message.id == messageId }
        if (null == queueMessage) {
            throw IllegalArgumentException("Message not found")
        }
        stack.remove(queueMessage)
        return true
    }

    private fun getOrCreateStackByName(queueName: String): MutableList<QueueMessage> {
        return stackMap.getOrPut(queueName) { mutableListOf() }
    }

}