import com.gabrielspassos.Solution
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class SolutionTest {

    private val testSolution: Solution = Solution()

    @Test
    fun testHello() {
        assertEquals("hello", testSolution.hello())
    }

    @Test
    fun testSolutionCalculateArea() {
        val input = listOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)

        val output = testSolution.calculateWaterArea(input)

        assertEquals(6, output)
    }

    @Test
    fun testSolutionToCalculateAreaTwo() {
        val input = listOf(1, 0, 0, 2)

        val output = testSolution.calculateWaterArea(input)

        assertEquals(2, output)
    }

    @Test
    fun testRankBots() {
        val expected = mapOf("[0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]" to 6, "[2, 0, 0, 3]" to 4, "[1, 0, 0, 2]" to 2)
        val input = listOf(listOf(1, 0, 0, 2), listOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1), listOf(2, 0, 0, 3))

        val output = testSolution.rankBots(input)

        assertEquals(expected, output)
    }

    @Test
    fun testRankBotsTwo() {
        val expected = mapOf("[1, 0, 0, 0, 0, 0, 1]" to 5, "[2, 0, 0, 3]" to 4, "[1, 0, 0, 2]" to 2)
        val input = listOf(listOf(1, 0, 1), listOf(1, 0, 0, 2), listOf(1, 0, 0, 0, 0, 0, 1), listOf(2, 0, 0, 3))

        val output = testSolution.rankBots(input)

        assertEquals(expected, output)
    }
}