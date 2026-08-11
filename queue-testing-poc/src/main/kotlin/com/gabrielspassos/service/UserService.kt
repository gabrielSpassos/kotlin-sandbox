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

    fun findEligibleUserForExchangeJob(): List<UserEntity> {
        val today = LocalDate.now()
        val sevenDaysAgo = today.minusDays(7)
        val sevenDaysAgoAtMidnight = LocalDateTime.of(sevenDaysAgo, LocalTime.MIDNIGHT)

        return userRepository
            .findByStatusAndUpdatedAtOlderThan(status = UserStatus.INACTIVE.name, thresholdDate = sevenDaysAgoAtMidnight)
    }

}