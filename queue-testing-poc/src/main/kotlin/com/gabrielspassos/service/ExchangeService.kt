package com.gabrielspassos.service

import com.gabrielspassos.client.ExchangeClient
import com.gabrielspassos.event.ExchangeEvent
import com.gabrielspassos.producer.ExchangeProducer
import com.gabrielspassos.repository.ExchangeRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ExchangeService(
    private val exchangeRepository: ExchangeRepository,
    private val userService: UserService,
    private val producer: ExchangeProducer,
    private val exchangeClient: ExchangeClient) {

    fun processExchangeJob(testInductionId: String? = null): Boolean {
        val users = userService.findEligibleUserForExchangeJob()

        if (users.isEmpty()) { return false }

        val usdResponse = exchangeClient.fetchUsdExchange

        if (Objects.isNull(usdResponse)) { return false }

        users.map { user -> ExchangeEvent(
            id = UUID.randomUUID().toString(),
            userId = user.id,
            usdToBrlRate = usdResponse?.usd?.brl,
            rateDateTime = usdResponse?.date,
        ) }.forEach { exchangeEvent -> producer.sendMessage(exchangeEvent, testInductionId) }

        return true
    }
    
}