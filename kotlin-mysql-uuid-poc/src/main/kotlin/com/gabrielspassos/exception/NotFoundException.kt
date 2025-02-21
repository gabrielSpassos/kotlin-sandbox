package com.gabrielspassos.exception

import org.springframework.http.HttpStatus

class NotFoundException(message: String) : HttpException(message, HttpStatus.NOT_FOUND.value())