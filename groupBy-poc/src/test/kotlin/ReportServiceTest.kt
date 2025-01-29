import com.gabrielspassos.ReportService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ReportServiceTest {

    @Test
    fun `should return report`() {
        val expected =
            """
            | Report POC
            | Total: 5
            | Inner List Count: 5
            | Success Count: 2
            | Failure Count: 3
            | Errors Report:
            | Error: error1 | Count: 1
            | Error: error2 | Count: 3
            | Error: error3 | Count: 2
            """.trimMargin()

        val reportService = ReportService()

        val report = reportService.getReport()

        assertEquals(expected, report)
    }
}