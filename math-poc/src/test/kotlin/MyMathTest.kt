import com.gabrielspassos.MyMath
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MyMathTest {

    @Test
    fun testDivide() {
     assertEquals(2, MyMath.divideIntRound(10, 5))
    }

    @Test
    fun testDivide2() {
        assertEquals(68992, MyMath.divideIntRound(344960, 5))
    }

    @Test
    fun testDivideOdd() {
        assertEquals(68993, MyMath.divideIntRound(344965, 5))
    }

    @Test
    fun testDivide3() {
        assertEquals(68994, MyMath.divideIntRound(344966, 5))
    }

    @Test
    fun testDivide4() {
        assertEquals(4, MyMath.divideIntRound(17, 5))
    }
 }