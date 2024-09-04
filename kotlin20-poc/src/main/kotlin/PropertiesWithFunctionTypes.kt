package com.gabrielspassos

class HolderOne(val provider: (() -> Unit)?) {
    fun process() {
        // In Kotlin 2.0.0, if provider isn't null, then
        // provider is smart-cast
        if (provider != null) {
            // The compiler knows that provider isn't null
            provider()

            // In 1.9.20, the compiler doesn't know that provider isn't
            // null, so it triggers an error:
            // Reference has a nullable type '(() -> Unit)?', use explicit '?.invoke()' to make a function-like call instead
        }
    }
}

interface PropertiesWithFunctionTypesProvider {
    operator fun invoke()
}

class PropertiesWithFunctionTypesProviderImpl : PropertiesWithFunctionTypesProvider {
    override fun invoke() {
        println("invoke")
    }
}

interface PropertiesWithFunctionTypesProcessor : () -> String

class HolderTwo(val provider: PropertiesWithFunctionTypesProvider?,
                val processor: PropertiesWithFunctionTypesProcessor?) {
    fun process() {
        if (provider != null) {
            provider()
            // In 1.9.20, the compiler triggers an error:
            // Reference has a nullable type 'Provider?' use explicit '?.invoke()' to make a function-like call instead
        }
    }
}

fun main() {
    HolderOne(null).process()
    HolderTwo(PropertiesWithFunctionTypesProviderImpl(), null).process()
}