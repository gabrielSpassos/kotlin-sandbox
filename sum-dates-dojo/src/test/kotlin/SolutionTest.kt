import com.gabrielspassos.Solution
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

internal class SolutionTest {

    private val solution = Solution()

    @Test
    fun testCalculateDateWithLib() {
        val expected = LocalDateTime.parse("2014-03-05 05:46:40", Solution.DATE_FORMATTER)
        val dateTime = LocalDateTime.parse("2014-03-04 02:00:00", Solution.DATE_FORMATTER)
        val seconds: Long = 100000

        val result = solution.calculateDateWithLib(dateTime, seconds)

        assertEquals(expected, result)
    }

    @Test
    fun testCalculateDate() {
        val expected = "2014-03-05 05:46:40"
        val date = "2014-03-04 02:00:00"
        val seconds: Long = 100000

        val result = solution.calculateDate(date, seconds)

        assertEquals(expected, result)
    }

    @Test
    fun testCalculateDate2() {
        val expected = "2014-03-04 02:00:30"
        val date = "2014-03-04 02:00:00"
        val seconds: Long = 30

        val result = solution.calculateDate(date, seconds)

        assertEquals(expected, result)
    }

    @Test
    fun testCalculateDate3() {
        val expected = "2014-03-04 02:01:10"
        val date = "2014-03-04 02:00:00"
        val seconds: Long = 70

        val result = solution.calculateDate(date, seconds)

        assertEquals(expected, result)
    }
}