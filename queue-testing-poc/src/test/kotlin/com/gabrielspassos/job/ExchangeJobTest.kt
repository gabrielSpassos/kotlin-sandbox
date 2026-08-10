package com.gabrielspassos.job

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.scheduling.annotation.Scheduled

class ExchangeJobTest {

    @Test
    fun `scheduled method should exist on exchange job`() {
        val method = ExchangeJob::class.java.getDeclaredMethod("runExchangeJob")
        val annotation = method.getAnnotation(Scheduled::class.java)

        assertNotNull(annotation)
    }
}
