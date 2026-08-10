package com.gabrielspassos.job

import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class ExchangeJob {

    private val logger: Log = LogFactory.getLog(javaClass)

    @Scheduled(fixedDelay = 6000)
    fun runExchangeJob() {
        logger.info("Exchange job executed")
    }
}