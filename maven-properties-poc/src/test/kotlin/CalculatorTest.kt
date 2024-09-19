import com.gabrielspassos.Calculator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CalculatorTest {

    @Test
    fun shouldReturn7() {
        val result = Calculator.add(4, 3)

        assertEquals(7, result)
    }
}