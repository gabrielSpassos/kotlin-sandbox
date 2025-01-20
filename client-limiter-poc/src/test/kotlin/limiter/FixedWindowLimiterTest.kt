package limiter

import com.gabrielspassos.limiter.FixedWindowLimiter
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class FixedWindowLimiterTest {

    @Test
    fun shouldAllowRequest() {
        // Arrange
        val fixedWindowLimiter = FixedWindowLimiter()
        val requestName = "test"

        // Act
        val result = fixedWindowLimiter.request(requestName)

        // Assert
        assertTrue(result)
    }

    @Test
    fun shouldNotAllowRequest() {
        // Arrange
        val fixedWindowLimiter = FixedWindowLimiter()
        val requestName = "test"

        // Act
        val result1 = fixedWindowLimiter.request(requestName)
        val result2 = fixedWindowLimiter.request(requestName)
        val result3 = fixedWindowLimiter.request(requestName)
        val result4 = fixedWindowLimiter.request(requestName)

        // Assert
        assertTrue(result1)
        assertTrue(result2)
        assertTrue(result3)
        assertFalse(result4)
    }
}