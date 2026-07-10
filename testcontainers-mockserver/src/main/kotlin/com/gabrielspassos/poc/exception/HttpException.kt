package com.gabrielspassos.poc.exception

open class HttpException(override val message: String?, val httpStatus: Int?, val code: String?) : RuntimeException(
    message
)