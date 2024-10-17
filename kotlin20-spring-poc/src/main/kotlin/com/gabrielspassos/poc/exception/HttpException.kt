package com.gabrielspassos.poc.exception

import com.gabrielspassos.poc.api.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

open class HttpException(
    private val httpStatus: HttpStatus,
    override val message: String,
    private val exception: Exception? = null,
): RuntimeException(message) {

    fun buildErrorResponse(): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(
            internalError = exception
                ?.let { buildInternalError(it) }
                ?.let { listOf(it) }
                ?: emptyList(),
            message = message,
            code = httpStatus.value().toString()
        )

        return ResponseEntity
            .status(httpStatus)
            .body(error)
    }

    private fun buildInternalError(exception: Exception): ErrorResponse {
        return ErrorResponse(message = exception.message ?: "no internal error message")
    }

}