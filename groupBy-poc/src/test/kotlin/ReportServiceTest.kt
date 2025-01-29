import com.gabrielspassos.ReportService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ReportServiceTest {

    @Test
    fun `should return report`() {
        val expected =
            """
            | Report POC
            | Total: 6
            | Inner List Count: 6
            | Success Count: 2
            | Failure Count: 4
            | Errors Report without IDS:
            | Error: error2 | Count: 4
            | Error: error3 | Count: 2
            | Error: error1 | Count: 1
            | Errors Report with IDS:
            | Error: error2 | Count: 4 | Users: [2, 3, 4, 6]
            | Error: error3 | Count: 2 | Users: [3, 4]
            | Error: error1 | Count: 1 | Users: [2]
            """.trimMargin()

        val reportService = ReportService()

        val report = reportService.getReport()

        assertEquals(expected, report)
    }
}