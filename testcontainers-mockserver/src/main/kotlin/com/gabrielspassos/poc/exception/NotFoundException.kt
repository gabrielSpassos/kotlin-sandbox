package com.gabrielspassos.poc.exception

import org.springframework.http.HttpStatus

class NotFoundException(message: String?, code: String?) : HttpException(message, HttpStatus.NOT_FOUND.value(), code)
