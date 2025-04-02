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

    @Test
    fun testSplitGroups() {
        val pair = MyMath.splitGroups(1)

        assertEquals(1, pair.first)
        assertEquals(10, pair.second)
    }

    @Test
    fun testSplitGroups2() {
        val pair = MyMath.splitGroups(2)

        assertEquals(11, pair.first)
        assertEquals(20, pair.second)
    }

    @Test
    fun testSplitGroups3() {
        val pair = MyMath.splitGroups(3)

        assertEquals(21, pair.first)
        assertEquals(30, pair.second)
    }

    @Test
    fun testSplitGroups4() {
        val pair = MyMath.splitGroups(4)

        assertEquals(31, pair.first)
        assertEquals(40, pair.second)
    }

    @Test
    fun testSplitGroups5() {
        val pair = MyMath.splitGroups(5)

        assertEquals(41, pair.first)
        assertEquals(50, pair.second)
    }

    @Test
    fun testSplitGroups6() {
        val pair = MyMath.splitGroups(6)

        assertEquals(51, pair.first)
        assertEquals(60, pair.second)
    }
 }