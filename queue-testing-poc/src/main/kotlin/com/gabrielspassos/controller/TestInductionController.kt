package com.gabrielspassos.controller

import com.gabrielspassos.annotation.NotProd
import com.gabrielspassos.controller.response.TestInductionExchangeJobResponse
import com.gabrielspassos.service.InductionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/v1/test-induction")
@NotProd
class TestInductionController(private val inductionService: InductionService) {

    @PostMapping("/exchange-job")
    fun triggerExchangeJob(@RequestParam(value = "id", required = false) id: String?)
    : ResponseEntity<TestInductionExchangeJobResponse> {
        val testInductionId = id ?: UUID.randomUUID().toString()
        val response = inductionService.processExchangeJob(testInductionId = testInductionId)
        return ResponseEntity.ok(response)
    }
}