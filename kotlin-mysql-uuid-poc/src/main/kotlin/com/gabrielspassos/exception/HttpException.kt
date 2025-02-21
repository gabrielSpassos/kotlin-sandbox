package com.gabrielspassos.exception

open class HttpException(message: String, httpStatus: Int) : RuntimeException(message)