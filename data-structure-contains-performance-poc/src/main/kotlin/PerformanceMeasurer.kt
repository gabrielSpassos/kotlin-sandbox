package com.gabrielspassos

object PerformanceMeasurer {

    fun <E> measureContainsItemTimeTaken(valueToFind: E, collection: Collection<E>): Pair<Boolean, Long> {
        val startTime = System.currentTimeMillis()
        val contains = collection.contains(valueToFind)
        return Pair(contains, System.currentTimeMillis() - startTime)
    }

}