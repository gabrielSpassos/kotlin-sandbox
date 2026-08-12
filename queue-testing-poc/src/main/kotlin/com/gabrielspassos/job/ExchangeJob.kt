package com.gabrielspassos.job

import com.gabrielspassos.service.InductionBatchService
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class ExchangeJob(private val inductionBatchService: InductionBatchService){

    private val logger: Log = LogFactory.getLog(javaClass)

    @Scheduled(cron = "0 0 12 * * *")
    fun runExchangeJob() {
        logger.info("Exchange job executed")
        inductionBatchService.processExchangeJob(testInductionId = null)
    }
}