package com.gabrielspassos.service

import com.gabrielspassos.client.ExchangeClient
import com.gabrielspassos.entity.ExchangeEntity
import com.gabrielspassos.event.ExchangeEvent
import com.gabrielspassos.exception.BadRequestException
import com.gabrielspassos.producer.ExchangeProducer
import com.gabrielspassos.repository.ExchangeRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import kotlin.jvm.optionals.getOrNull

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

    fun saveExchange(exchangeEvent: ExchangeEvent): ExchangeEntity {
        val alreadyExistingExchangeEntity = exchangeEvent.userId
                ?.let { userId -> exchangeRepository.findByUserId(userId) }
                ?.getOrNull()

        if (exchangeEvent.usdToBrlRate == null || exchangeEvent.rateDateTime.isNullOrBlank()) {
            throw BadRequestException("Invalid exchange event rate values", "INVALID_EXCHANGE_EVENT")
        }

        val exchangeEntityToSave = if (alreadyExistingExchangeEntity != null) {
            alreadyExistingExchangeEntity.copy(
                usdToBrlExchangeRate = exchangeEvent.usdToBrlRate,
                rateDateTime = LocalDateTime.parse(exchangeEvent.rateDateTime))
        } else {
            ExchangeEntity(
                id = null,
                userId = exchangeEvent.userId!!,
                usdToBrlExchangeRate = exchangeEvent.usdToBrlRate,
                rateDateTime = LocalDateTime.parse(exchangeEvent.rateDateTime),
            )
        }

        return exchangeRepository.save(exchangeEntityToSave)
    }

    internal fun deleteExchange(exchangeEntity: ExchangeEntity): Boolean {
        exchangeRepository.delete(exchangeEntity)
        return true
    }
}