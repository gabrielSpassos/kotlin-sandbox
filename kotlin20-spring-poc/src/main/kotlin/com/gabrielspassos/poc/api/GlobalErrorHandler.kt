package com.gabrielspassos.poc.api

import com.gabrielspassos.poc.api.response.ErrorResponse
import com.gabrielspassos.poc.exception.HttpException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody

@ControllerAdvice
class GlobalErrorHandler {

    @ResponseBody
    @ExceptionHandler(HttpException::class)
    fun errorResponse(exception: HttpException): ResponseEntity<ErrorResponse> {
        return exception.buildErrorResponse()
    }

}