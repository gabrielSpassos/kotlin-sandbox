package com.gabrielspassos.poc.exception

import org.springframework.http.HttpStatus

class NotFoundException(message: String, exception: Exception? = null)
    : HttpException(HttpStatus.NOT_FOUND, message, exception)