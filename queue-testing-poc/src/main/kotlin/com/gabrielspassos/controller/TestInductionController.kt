package com.gabrielspassos.controller

import com.gabrielspassos.service.BatchService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/v1/test-induction")
class TestInductionController(private val batchService: BatchService) {

    @PostMapping("/exchange-job")
    fun triggerExchangeJob(@RequestParam(value = "id", required = false) id: String?) {
        val testInductionId = id ?: UUID.randomUUID().toString()
        batchService.processExchangeJob(testInductionId = testInductionId)
    }
}