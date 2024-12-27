package com.gabrielspassos.consumer.client

import com.gabrielspassos.consumer.model.QueueMessage
import java.util.Optional

interface QueueService {

    fun addMessage(queueName: String, queueMessage: QueueMessage): Boolean

    fun consumeMessage(queueName: String): Optional<QueueMessage>

    fun consumeMessages(queueName: String, batchSize: Int = 10): List<QueueMessage>

    fun deleteMessage(queueName: String, messageId: String): Boolean

}