package consumer.client

import com.gabrielspassos.consumer.client.InMemoryQueueClient
import com.gabrielspassos.consumer.model.QueueMessage
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class InMemoryQueueClientTest {

    @Test
    fun shouldAddMessage() {
        val inMemoryQueueClient = InMemoryQueueClient()
        val queueName = "unit-test-queue"
        val queueMessage = QueueMessage("1", "message 1")

        val result = inMemoryQueueClient.addMessage(queueName, queueMessage)

        assertTrue { result }
    }

    @Test
    fun shouldAddMessageAndConsumeIt() {
        val inMemoryQueueClient = InMemoryQueueClient()
        val queueName = "unit-test-queue"
        val queueMessage = QueueMessage("2", "message 2")

        val result = inMemoryQueueClient.addMessage(queueName, queueMessage)
        val consumedMessage = inMemoryQueueClient.consumeMessage(queueName)

        assertTrue { result }
        assertTrue { consumedMessage.isPresent }
        assertEquals("2", consumedMessage.get().id)
        assertEquals("message 2", consumedMessage.get().body)
    }

    @Test
    fun shouldAddMessagesAndConsumeInBatch() {
        val inMemoryQueueClient = InMemoryQueueClient()
        val queueName = "unit-test-queue"

        for (i in 1..5) {
            val queueMessage = QueueMessage(i.toString(), "message $i")
            inMemoryQueueClient.addMessage(queueName, queueMessage)
        }

        val consumedMessages = inMemoryQueueClient.consumeMessages(queueName, 3)

        assertEquals(3, consumedMessages.size)
        assertEquals("5", consumedMessages[0].id)
        assertEquals("4", consumedMessages[1].id)
        assertEquals("3", consumedMessages[2].id)
    }

    @Test
    fun shouldDeleteMessage() {
        val inMemoryQueueClient = InMemoryQueueClient()
        val queueName = "unit-test-queue"
        val queueMessage = QueueMessage("3", "message 3")

        inMemoryQueueClient.addMessage(queueName, queueMessage)
        val result = inMemoryQueueClient.deleteMessage(queueName,"3")

        assertTrue { result }
    }

    @Test
    fun shouldFailToDeleteMessage() {
        val inMemoryQueueClient = InMemoryQueueClient()
        val queueName = "unit-test-queue"

        assertThrows<IllegalArgumentException> { inMemoryQueueClient.deleteMessage(queueName, "foo") }
    }
}