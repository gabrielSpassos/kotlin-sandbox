package com.gabrielspassos.exception

import org.springframework.http.HttpStatus

class BadRequestException(message: String?, code: String?) : HttpException(message, HttpStatus.BAD_REQUEST.value(), code)
