package com.gabrielspassos.e

import java.util.UUID

object ExceptionHandling {

    fun testString() {
        var stringInput: String? = null
        // stringInput is smart-cast to String type
        stringInput = UUID.randomUUID().toString()
        try {
            println("String=$stringInput has length=${stringInput.length}")

            // smart-cast knows that the value is nullable
            stringInput = null

            throw RuntimeException("intended exception")
        } catch (e: Exception) {
            println("String=$stringInput has length=${stringInput?.length}")
        }
    }

}

fun main() {
    ExceptionHandling.testString()
}