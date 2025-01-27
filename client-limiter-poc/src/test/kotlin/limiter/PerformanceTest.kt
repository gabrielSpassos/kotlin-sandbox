package limiter

import com.gabrielspassos.limiter.FixedWindowLimiter
import com.gabrielspassos.limiter.SlidingWindowLimiter
import org.junit.jupiter.api.Test

class PerformanceTest {

    @Test
    fun testPerformanceWith100Requests() {
        val fixedWindowLimiter = FixedWindowLimiter()
        val slidingWindowLimiter = SlidingWindowLimiter()
        val requestsNumber = 100
        val requestName = "performanceTest1"

        val fixedStartTime = System.currentTimeMillis()
        for (i in 1..requestsNumber) {
            fixedWindowLimiter.isRequestAcceptable(requestName)
        }
        val fixedEndTime = System.currentTimeMillis()
        println("Fixed: Time taken for $requestsNumber requests: ${fixedEndTime - fixedStartTime} ms")

        val slidingStartTime = System.currentTimeMillis()
        for (i in 1..requestsNumber) {
            slidingWindowLimiter.isRequestAcceptable(requestName)
        }
        val slidingEndTime = System.currentTimeMillis()
        println("Sliding: Time taken for $requestsNumber requests: ${slidingEndTime - slidingStartTime} ms")
    }

    @Test
    fun testPerformanceWith1000Requests() {
        val fixedWindowLimiter = FixedWindowLimiter()
        val slidingWindowLimiter = SlidingWindowLimiter()
        val requestsNumber = 1000
        val requestName = "performanceTest2"

        val fixedStartTime = System.currentTimeMillis()
        for (i in 1..requestsNumber) {
            fixedWindowLimiter.isRequestAcceptable(requestName)
        }
        val fixedEndTime = System.currentTimeMillis()
        println("Fixed: Time taken for $requestsNumber requests: ${fixedEndTime - fixedStartTime} ms")

        val slidingStartTime = System.currentTimeMillis()
        for (i in 1..requestsNumber) {
            slidingWindowLimiter.isRequestAcceptable(requestName)
        }
        val slidingEndTime = System.currentTimeMillis()
        println("Sliding: Time taken for $requestsNumber requests: ${slidingEndTime - slidingStartTime} ms")
    }

    @Test
    fun testPerformanceWith1000000Requests() {
        val fixedWindowLimiter = FixedWindowLimiter()
        val slidingWindowLimiter = SlidingWindowLimiter()
        val requestsNumber = 1000000
        val requestName = "performanceTest3"

        val fixedStartTime = System.currentTimeMillis()
        for (i in 1..requestsNumber) {
            fixedWindowLimiter.isRequestAcceptable(requestName)
        }
        val fixedEndTime = System.currentTimeMillis()
        println("Fixed: Time taken for $requestsNumber requests: ${fixedEndTime - fixedStartTime} ms")

        val slidingStartTime = System.currentTimeMillis()
        for (i in 1..requestsNumber) {
            slidingWindowLimiter.isRequestAcceptable(requestName)
        }
        val slidingEndTime = System.currentTimeMillis()
        println("Sliding: Time taken for $requestsNumber requests: ${slidingEndTime - slidingStartTime} ms")
    }

    @Test
    fun testPerformanceWith10000000Requests() {
        val fixedWindowLimiter = FixedWindowLimiter()
        val slidingWindowLimiter = SlidingWindowLimiter()
        val requestsNumber = 10000000
        val requestName = "performanceTest4"

        val fixedStartTime = System.currentTimeMillis()
        for (i in 1..requestsNumber) {
            fixedWindowLimiter.isRequestAcceptable(requestName)
        }
        val fixedEndTime = System.currentTimeMillis()
        println("Fixed: Time taken for $requestsNumber requests: ${fixedEndTime - fixedStartTime} ms")

        val slidingStartTime = System.currentTimeMillis()
        for (i in 1..requestsNumber) {
            slidingWindowLimiter.isRequestAcceptable(requestName)
        }
        val slidingEndTime = System.currentTimeMillis()
        println("Sliding: Time taken for $requestsNumber requests: ${slidingEndTime - slidingStartTime} ms")
    }
}