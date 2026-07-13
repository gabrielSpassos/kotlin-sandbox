package com.gabrielspassos.event

import java.math.BigDecimal
import java.time.LocalDateTime

data class UserEvent(private val id: String,
                     private val usdRate: BigDecimal,
                     private val rateDateTime: LocalDateTime)
