package com.gabrielspassos.poc.api.response

data class ErrorResponse(
    val internalError: List<ErrorResponse> = emptyList(),
    val message: String,
    val code: String? = null
)
