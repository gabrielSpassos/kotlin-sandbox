package com.gabrielspassos

object MyMath {

    fun divideIntRound(value: Long, divisor: Long): Long {
        val result = value.toDouble() / divisor.toDouble()
        return Math.ceil(result).toLong()
    }
}