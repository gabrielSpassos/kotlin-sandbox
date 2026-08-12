package com.gabrielspassos.service

import com.gabrielspassos.entity.UserEntity
import com.gabrielspassos.entity.UserStatus
import com.gabrielspassos.repository.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Service
class UserService (private val userRepository: UserRepository) {

    companion object {
        const val EXCHANGE_JOB_DATE_THRESHOLD: Long = 7
    }

    fun findEligibleUserForExchangeJob(): List<UserEntity> {
        val today = LocalDate.now()
        val sevenDaysAgo = today.minusDays(EXCHANGE_JOB_DATE_THRESHOLD)
        val sevenDaysAgoAtMidnight = LocalDateTime.of(sevenDaysAgo, LocalTime.MIDNIGHT)

        return userRepository
            .findByStatusAndUpdatedAtOlderThan(status = UserStatus.INACTIVE.name, thresholdDate = sevenDaysAgoAtMidnight)
    }

    fun save(user: UserEntity): UserEntity {
        return userRepository.save(user)
    }

    internal fun delete(user: UserEntity): Boolean {
        userRepository.delete(user)
        return true
    }

}