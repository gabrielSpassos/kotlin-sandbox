package consumer

import com.gabrielspassos.consumer.BankConsumer
import com.gabrielspassos.consumer.client.InMemoryQueueClient
import com.gabrielspassos.consumer.model.QueueMessage
import com.gabrielspassos.dto.BankDTO
import com.gabrielspassos.service.BankService
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.util.Optional
import java.util.UUID
import kotlin.test.assertEquals

@ExtendWith(MockitoExtension::class)
class BankConsumerTest {

 @Mock
 private lateinit var bankService: BankService

 @Mock
 private lateinit var queueService: InMemoryQueueClient

     @Test
     fun shouldConsumeMessage() {
         // given
         val bankConsumer = BankConsumer(bankService, queueService)
         val bankDTO = BankDTO(1L, "Brazil Bank", "100-queue")
         val messageId = UUID.randomUUID().toString()
         val queueName = "bank-queue"
         val queueMessage = QueueMessage(messageId, "{\"id\":1,\"name\":\"Brazil Bank\",\"code\":\"100-queue\"}")

         given(queueService.consumeMessage(queueName)).willReturn(Optional.of(queueMessage))
         given(bankService.update(bankDTO)).willReturn(bankDTO)
         given(queueService.deleteMessage(queueName, messageId)).willReturn(true)

          // when
          val result = bankConsumer.processSingleMessage()

         // then
         assertTrue { result }
     }

    @Test
    fun shouldFailToProcessMessage() {
        // given
        val bankConsumer = BankConsumer(bankService, queueService)
        val messageId = UUID.randomUUID().toString()
        val queueName = "bank-queue"
        val dlqName = "bank-dlq-queue"
        val bankDTO = BankDTO(1L, "Brazil Bank", "100-queue")
        val queueMessage = QueueMessage(messageId, "{\"id\":1,\"name\":\"Brazil Bank\",\"code\":\"100-queue\"}")

        given(queueService.consumeMessage(queueName)).willReturn(Optional.of(queueMessage))
        given(bankService.update(bankDTO)).willThrow(RuntimeException("Error to update bank"))
        given(queueService.addMessage(dlqName, queueMessage)).willReturn(true)

        // when
        val result = bankConsumer.processSingleMessage()

        // then
        assertFalse { result }
    }

    @Test
    fun shouldProcessMessages() {
        // given
        val bankConsumer = BankConsumer(bankService, queueService)
        val queueName = "bank-queue"

        val messageId1 = UUID.randomUUID().toString()
        val queueMessage1 = QueueMessage(messageId1, "{\"id\":1,\"name\":\"Brazil Bank\",\"code\":\"100-queue\"}")
        val bankDTO1 = BankDTO(1L, "Brazil Bank", "100-queue")

        val messageId2 = UUID.randomUUID().toString()
        val queueMessage2 = QueueMessage(messageId2, "{\"id\":2,\"name\":\"Argentina Bank\",\"code\":\"200-queue\"}")
        val bankDTO2 = BankDTO(2L, "Argentina Bank", "200-queue")

        val messageId3 = UUID.randomUUID().toString()
        val queueMessage3 = QueueMessage(messageId3, "{\"id\":3,\"name\":\"Peru Bank\",\"code\":\"300-queue\"}")
        val bankDTO3 = BankDTO(3L, "Peru Bank", "300-queue")

        given(queueService.consumeMessages(queueName, 5))
            .willReturn(listOf(queueMessage1, queueMessage2, queueMessage3))
        given(bankService.update(bankDTO1)).willReturn(bankDTO1)
        given(bankService.update(bankDTO2)).willReturn(bankDTO2)
        given(bankService.update(bankDTO3)).willReturn(bankDTO3)
        given(queueService.deleteMessage(queueName, messageId1)).willReturn(true)
        given(queueService.deleteMessage(queueName, messageId2)).willReturn(true)
        given(queueService.deleteMessage(queueName, messageId3)).willReturn(true)

        // when
        val result = bankConsumer.processMultipleMessages()

        // then
        assertTrue { result }
    }

    @Test
    fun shouldPartiallyProcessMessages() {
        // given
        val bankConsumer = BankConsumer(bankService, queueService)
        val queueName = "bank-queue"
        val dlqName = "bank-dlq-queue"

        val messageId1 = UUID.randomUUID().toString()
        val queueMessage1 = QueueMessage(messageId1, "{\"id\":1,\"name\":\"Brazil Bank\",\"code\":\"100-queue\"}")
        val bankDTO1 = BankDTO(1L, "Brazil Bank", "100-queue")

        val messageId2 = UUID.randomUUID().toString()
        val queueMessage2 = QueueMessage(messageId2, "{\"id\":2,\"name\":\"Argentina Bank\",\"code\":\"200-queue\"}")
        val bankDTO2 = BankDTO(2L, "Argentina Bank", "200-queue")

        val messageId3 = UUID.randomUUID().toString()
        val queueMessage3 = QueueMessage(messageId3, "{\"id\":3,\"name\":\"Peru Bank\",\"code\":\"300-queue\"}")
        val bankDTO3 = BankDTO(3L, "Peru Bank", "300-queue")

        given(queueService.consumeMessages(queueName, 5))
            .willReturn(listOf(queueMessage1, queueMessage2, queueMessage3))
        given(bankService.update(bankDTO1)).willReturn(bankDTO1)
        given(bankService.update(bankDTO2)).willThrow(RuntimeException("Error to update bank"))
        given(bankService.update(bankDTO3)).willReturn(bankDTO3)
        given(queueService.deleteMessage(queueName, messageId1)).willReturn(true)
        //todo: the test pass with or without this mock
        given(queueService.addMessage(dlqName, queueMessage2)).willReturn(true)
        given(queueService.deleteMessage(queueName, messageId3)).willReturn(true)

        // when
        val result = bankConsumer.processMultipleMessages()

        // then
        assertFalse { result }
    }

    @Test
    fun shouldPartiallyProcessMessagesEvenWithoutFallbackMock() {
        // given
        val bankConsumer = BankConsumer(bankService, queueService)
        val queueName = "bank-queue"
        val dlqName = "bank-dlq-queue"

        val messageId1 = UUID.randomUUID().toString()
        val queueMessage1 = QueueMessage(messageId1, "{\"id\":1,\"name\":\"Brazil Bank\",\"code\":\"100-queue\"}")
        val bankDTO1 = BankDTO(1L, "Brazil Bank", "100-queue")

        val messageId2 = UUID.randomUUID().toString()
        val queueMessage2 = QueueMessage(messageId2, "{\"id\":2,\"name\":\"Argentina Bank\",\"code\":\"200-queue\"}")
        val bankDTO2 = BankDTO(2L, "Argentina Bank", "200-queue")

        val messageId3 = UUID.randomUUID().toString()
        val queueMessage3 = QueueMessage(messageId3, "{\"id\":3,\"name\":\"Peru Bank\",\"code\":\"300-queue\"}")
        val bankDTO3 = BankDTO(3L, "Peru Bank", "300-queue")

        given(queueService.consumeMessages(queueName, 5))
            .willReturn(listOf(queueMessage1, queueMessage2, queueMessage3))
        given(bankService.update(bankDTO1)).willReturn(bankDTO1)
        given(bankService.update(bankDTO2)).willThrow(RuntimeException("Error to update bank"))
        given(bankService.update(bankDTO3)).willReturn(bankDTO3)
        given(queueService.deleteMessage(queueName, messageId1)).willReturn(true)
        given(queueService.deleteMessage(queueName, messageId3)).willReturn(true)

        // when
        val result = bankConsumer.processMultipleMessages()

        // then
        assertFalse { result }
    }

    @Test
    fun shouldPartiallyProcessMessagesWithCaptor() {
        // given
        val bankConsumer = BankConsumer(bankService, queueService)
        val queueName = "bank-queue"
        val dlqName = "bank-dlq-queue"
        val captor: ArgumentCaptor<QueueMessage> = ArgumentCaptor.forClass(QueueMessage::class.java)

        val messageId1 = UUID.randomUUID().toString()
        val queueMessage1 = QueueMessage(messageId1, "{\"id\":1,\"name\":\"Brazil Bank\",\"code\":\"100-queue\"}")
        val bankDTO1 = BankDTO(1L, "Brazil Bank", "100-queue")

        val messageId2 = UUID.randomUUID().toString()
        val queueMessage2 = QueueMessage(messageId2, "{\"id\":2,\"name\":\"Argentina Bank\",\"code\":\"200-queue\"}")
        val bankDTO2 = BankDTO(2L, "Argentina Bank", "200-queue")

        val messageId3 = UUID.randomUUID().toString()
        val queueMessage3 = QueueMessage(messageId3, "{\"id\":3,\"name\":\"Peru Bank\",\"code\":\"300-queue\"}")
        val bankDTO3 = BankDTO(3L, "Peru Bank", "300-queue")

        given(queueService.consumeMessages(queueName, 5))
            .willReturn(listOf(queueMessage1, queueMessage2, queueMessage3))
        given(bankService.update(bankDTO1)).willReturn(bankDTO1)
        given(bankService.update(bankDTO2)).willThrow(RuntimeException("Error to update bank"))
        given(bankService.update(bankDTO3)).willReturn(bankDTO3)
        given(queueService.deleteMessage(queueName, messageId1)).willReturn(true)
        given(queueService.addMessage(eqObject(dlqName), captureObject(captor))).willReturn(true)
        given(queueService.deleteMessage(queueName, messageId3)).willReturn(true)

        // when
        val result = bankConsumer.processMultipleMessages()

        // then
        assertFalse { result }
        val capturedValue = captor.value
        assertEquals(messageId2, capturedValue.id)
    }

    private fun <T> captureObject(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()
    private fun <T> eqObject(value: T): T = ArgumentMatchers.eq(value)

}