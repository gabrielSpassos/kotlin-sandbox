package com.gabrielspassos.service

import org.springframework.stereotype.Service

@Service
class BatchService(private val exchangeService: ExchangeService) {

    fun processExchangeJob(testInductionId: String? = null): Boolean {
        return exchangeService.processExchangeJob(testInductionId = testInductionId)
    }

}