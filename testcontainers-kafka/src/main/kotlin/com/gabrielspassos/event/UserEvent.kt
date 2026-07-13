package com.gabrielspassos.event

import java.math.BigDecimal

data class UserEvent(val id: String,
                     val usdRate: BigDecimal,
                     val rateDateTime: String)
