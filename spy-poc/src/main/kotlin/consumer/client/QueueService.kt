package com.gabrielspassos.consumer.client

import com.gabrielspassos.consumer.model.QueueMessage
import java.util.Optional

interface QueueService {

    fun addMessage(queueMessage: QueueMessage): Boolean

    fun consumeMessage(): Optional<QueueMessage>

    fun deleteMessage(messageId: String): Boolean

}