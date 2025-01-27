package limiter

import com.gabrielspassos.limiter.SlidingWindowLimiter
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class SlidingWindowLimiterTest {

    @Test
    fun `should return true when request is acceptable`() {
        val slidingWindowLimiter = SlidingWindowLimiter()
        val requestName = "test"

        val result = slidingWindowLimiter.isRequestAcceptable(requestName)

        assertTrue(result)
    }

    @Test
    fun `should return false when request is not acceptable`() {
        val slidingWindowLimiter = SlidingWindowLimiter()
        val requestName = "test"

        val result1 = slidingWindowLimiter.isRequestAcceptable(requestName)
        val result2 = slidingWindowLimiter.isRequestAcceptable(requestName)
        val result3 = slidingWindowLimiter.isRequestAcceptable(requestName)
        val result4 = slidingWindowLimiter.isRequestAcceptable(requestName)

        assertTrue(result1)
        assertTrue(result2)
        assertTrue(result3)
        assertFalse(result4)
    }

    @Test
    fun `should allow request after some time`() {
        val slidingWindowLimiter = SlidingWindowLimiter()
        val requestName = "test"

        val result1 = slidingWindowLimiter.isRequestAcceptable(requestName)
        val result2 = slidingWindowLimiter.isRequestAcceptable(requestName)
        val result3 = slidingWindowLimiter.isRequestAcceptable(requestName)
        val result4 = slidingWindowLimiter.isRequestAcceptable(requestName)
        Thread.sleep(1000)
        val result5 = slidingWindowLimiter.isRequestAcceptable(requestName)
        val result6 = slidingWindowLimiter.isRequestAcceptable(requestName)
        val result7 = slidingWindowLimiter.isRequestAcceptable(requestName)
        val result8 = slidingWindowLimiter.isRequestAcceptable(requestName)

        assertTrue(result1)
        assertTrue(result2)
        assertTrue(result3)
        assertFalse(result4)
        assertTrue(result5)
        assertTrue(result6)
        assertTrue(result7)
        assertFalse(result8)
    }
}