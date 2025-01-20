package com.gabrielspassos.limiter

class FixedWindowLimiter {

    private val limitMap = mutableMapOf<String, FixedWindow>()
    private val threshold = 3
    private val periodInSeconds: Long = 1

    fun request(requestName: String): Boolean {
        val fixedWindow = limitMap[requestName]
        val currentTime = System.currentTimeMillis()

        if (fixedWindow == null || currentTime - fixedWindow.timestamp > secondsToMillis(periodInSeconds)) {
            limitMap[requestName] = FixedWindow(1, currentTime)
            return true
        } else {
            // check if we have breached the count already
            if (fixedWindow.count < threshold) {
                val updatedWindow = FixedWindow(fixedWindow.count + 1, currentTime)
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

private data class FixedWindow(val count: Int, val timestamp: Long)