package com.gabrielspassos.service

import com.gabrielspassos.controller.response.TestInductionExchangeJobResponse
import com.gabrielspassos.entity.UserEntity
import com.gabrielspassos.entity.UserStatus
import com.gabrielspassos.event.ExchangeEvent
import com.gabrielspassos.service.UserService.Companion.EXCHANGE_JOB_DATE_THRESHOLD
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import org.springframework.core.env.Environment
import java.util.Optional

@Service
class InductionService(
    private val exchangeService: ExchangeService,
    private val userService: UserService,
    private val environment: Environment
) {

    private val logger: Log = LogFactory.getLog(javaClass)
    private val userMap: ConcurrentMap<String, UserEntity> = ConcurrentHashMap()

    fun processExchangeJob(testInductionId: String? = null): TestInductionExchangeJobResponse {
        val userTestInduction = if (isTestInduction(testInductionId)) {
            logger.info("testInductionId=$testInductionId start processing exchange job")

            val today = LocalDate.now()
            val eightDaysAgo = today.minusDays(EXCHANGE_JOB_DATE_THRESHOLD + 1)
            val eightDaysAgoAtMidnight = LocalDateTime.of(eightDaysAgo, LocalTime.MIDNIGHT)

            val userToSave = UserEntity(
                name = "username-test-induction-$testInductionId",
                status = UserStatus.INACTIVE.name,
                updatedAt = eightDaysAgoAtMidnight
            )

            val userTestInduction = userService.saveUser(userToSave)
            userMap[testInductionId] = userTestInduction
            logger.info("testInductionId=$testInductionId saved userId=${userTestInduction.id}")
            Optional.ofNullable(userTestInduction)
        } else {
            Optional.empty()
        }

        exchangeService.processExchangeJob(testInductionId = testInductionId)

        return TestInductionExchangeJobResponse(
            userId = userTestInduction.map { it.id }.orElse(null),
            username = userTestInduction.map { it.name }.orElse(null),
        )
    }

    fun processExchangeConsumerEvent(exchangeEvent: ExchangeEvent, testInductionId: String? = null): Boolean {
        val exchangeEntity = exchangeService.saveExchange(exchangeEvent)

        if (isTestInduction(testInductionId)) {
            logger.info("testInductionId=$testInductionId finished processing exchange consume event")
            val user = userMap[testInductionId]
            user?.let { userService.deleteUser(it) }
            exchangeEntity.let { exchangeService.deleteExchange(it) }
            logger.info("testInductionId=$testInductionId deleted userId=${user?.id} and exchangeId=${exchangeEntity.id}")
        }

        return true
    }

    private fun isTestInduction(testInductionId: String? = null): Boolean {
        return isNotProdEnv() && testInductionId?.isNotBlank() == true
    }

    private fun isNotProdEnv(): Boolean {
        return !environment.activeProfiles.contains("prod")
    }

}