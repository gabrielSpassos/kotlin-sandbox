package com.gabrielspassos.c

interface Processor {
    fun process() {
        println("running process")
    }
}

inline fun inlineAction(f: () -> Unit) = f()

fun nextProcessor(): Processor? = null

fun runProcessor(): Processor? {
    var processor: Processor? = null
    inlineAction {
        if (processor != null) {
            processor.process()

            // In Kotlin 1.9.20: processor?.process()
        }

        processor = nextProcessor()
    }

    return processor
}

fun main() {
    runProcessor()
}