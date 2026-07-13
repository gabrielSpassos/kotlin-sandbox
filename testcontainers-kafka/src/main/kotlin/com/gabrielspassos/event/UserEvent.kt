package com.gabrielspassos.event

import java.math.BigDecimal

data class UserEvent(
    val id: String? = null,
    val usdRate: BigDecimal? = null,
    val rateDateTime: String? = null
)
