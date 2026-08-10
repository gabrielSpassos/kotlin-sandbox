package com.gabrielspassos.event

import java.math.BigDecimal

data class ExchangeEvent(
    val id: String? = null,
    val usdRate: BigDecimal? = null,
    val rateDateTime: String? = null
)