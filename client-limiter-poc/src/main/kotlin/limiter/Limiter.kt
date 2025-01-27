package com.gabrielspassos.limiter

interface Limiter {

    fun isRequestAcceptable(requestName: String): Boolean

}