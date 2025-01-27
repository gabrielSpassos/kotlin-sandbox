package com.gabrielspassos.limiter

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger

class FixedWindowLimiter: Limiter {

    private val limitMap: ConcurrentHashMap<String, FixedWindow> = ConcurrentHashMap()
    private val threshold = 3
    private val periodInSeconds: Long = 1

    override fun isRequestAcceptable(requestName: String): Boolean {
        val fixedWindow = limitMap[requestName]
        val currentTime = System.currentTimeMillis()

        if (fixedWindow == null || currentTime - fixedWindow.timestamp > secondsToMillis(periodInSeconds)) {
            limitMap[requestName] = FixedWindow(AtomicInteger(1), currentTime)
            return true
        } else {
            // check if we have breached the count already
            if (fixedWindow.count.get() < threshold) {
                fixedWindow.count.incrementAndGet()
                val updatedWindow = FixedWindow(fixedWindow.count, currentTime)
                limitMap[requestName] = updatedWindow
                return true
            } else {
                return false
            }
        }
    }

    private fun secondsToMillis(seconds: Long): Long {
        return seconds * 1000L
    }
}

private data class FixedWindow(val count: AtomicInteger, val timestamp: Long)