package consumer

import com.gabrielspassos.consumer.BankConsumer
import com.gabrielspassos.consumer.client.InMemoryQueueClient
import com.gabrielspassos.consumer.model.QueueMessage
import com.gabrielspassos.dto.BankDTO
import com.gabrielspassos.service.BankService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.util.Optional
import java.util.UUID
import kotlin.test.assertTrue

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
     val queueMessage = QueueMessage(messageId, "{\"id\":1,\"name\":\"Brazil Bank\",\"code\":\"100-queue\"}")

     given(queueService.consumeMessage()).willReturn(Optional.of(queueMessage))
     given(bankService.update(bankDTO)).willReturn(bankDTO)
     given(queueService.deleteMessage(messageId)).willReturn(true)

      // when
      val result = bankConsumer.consume()

     // then
     assertTrue { result }
 }

}