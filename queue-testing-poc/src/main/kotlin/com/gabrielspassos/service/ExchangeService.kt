package com.gabrielspassos.service

import com.gabrielspassos.client.ExchangeClient
import com.gabrielspassos.event.ExchangeEvent
import com.gabrielspassos.producer.ExchangeProducer
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.Objects
import java.util.UUID

@Service
class ExchangeService(
    private val producer: ExchangeProducer,
    private val exchangeClient: ExchangeClient) {

    fun processExchangeJob(testInductionId: String? = null): Boolean {
        val usdResponse = exchangeClient.fetchUsdExchange

        if (Objects.isNull(usdResponse)) {
            return false
        }

        val exchangeEvent = ExchangeEvent(
            id = testInductionId ?: UUID.randomUUID().toString(),
            usdToBrlRate = usdResponse?.usd?.brl,
            rateDateTime = usdResponse?.date,
        )

        return producer.sendMessage(exchangeEvent)
    }
}