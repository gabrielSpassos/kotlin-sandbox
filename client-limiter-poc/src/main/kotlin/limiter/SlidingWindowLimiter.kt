package com.gabrielspassos.limiter

import java.util.Deque
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedDeque

class SlidingWindowLimiter: Limiter {

    private val limitMap: ConcurrentHashMap<String, Deque<Long>> = ConcurrentHashMap()
    private val threshold = 3
    private val periodInSeconds: Long = 1

    override fun isRequestAcceptable(requestName: String): Boolean {
        val deque = limitMap[requestName]
        val currentTime = System.currentTimeMillis()
        // first check if this is the first request, if that is the case the deque won't be initialized
        if (null == deque) {
            val newDeque = ConcurrentLinkedDeque<Long>()
            newDeque.add(currentTime)
            limitMap[requestName] = newDeque
            return true
        }
        // keep on removing the timestamps that are no longer the t to t - 1s window
        while (!deque.isEmpty() && currentTime - deque.first > secondsToMillis(periodInSeconds)) {
            deque.removeFirst()
        }

        // if that window has more than the 3 request, drop it
        if (deque.size >= threshold) {
            return false
        }

        // otherwise add it
        deque.addLast(currentTime);
        return true

    }

    private fun secondsToMillis(seconds: Long): Long {
        return seconds * 1000L
    }
}