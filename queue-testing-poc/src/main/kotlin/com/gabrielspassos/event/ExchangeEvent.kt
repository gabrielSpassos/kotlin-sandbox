package com.gabrielspassos.event

import java.math.BigDecimal
import java.util.UUID

data class ExchangeEvent(
    val id: String? = null,
    val userId: UUID? = null,
    val usdToBrlRate: BigDecimal? = null,
    val rateDateTime: String? = null
)