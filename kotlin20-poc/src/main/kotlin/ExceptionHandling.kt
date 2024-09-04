package com.gabrielspassos

import java.util.UUID

object ExceptionHandling {

    fun testString() {
        var stringInput: String? = null
        // stringInput is smart-cast to String type
        stringInput = UUID.randomUUID().toString()
        try {
            // The compiler knows that stringInput isn't null
            println(stringInput.length)
            // 0

            // The compiler rejects previous smart cast information for
            // stringInput. Now stringInput has the String? type.
            stringInput = null

            // Trigger an exception
            throw RuntimeException("intended exception")
        } catch (exception: Exception) {
            // In Kotlin 2.0.0, the compiler knows stringInput
            // can be null, so stringInput stays nullable.
            println(stringInput?.length)
            // null

            // In Kotlin 1.9.20, the compiler says that a safe call isn't
            // needed, but this is incorrect.
        }
    }

}

fun main() {
    ExceptionHandling.testString()
}