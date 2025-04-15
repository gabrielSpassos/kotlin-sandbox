package com.gabrielspassos.controller.v1.request

import java.math.BigDecimal

data class ProductRequest(
    val name: String? = null,
    val category: String? = null,
    val value: BigDecimal? = null,
    val isActive: Boolean = false
)
