import com.gabrielspassos.InternalObject
import com.gabrielspassos.OutsideObject
import com.gabrielspassos.ReportService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ReportServiceTest {

    @Test
    fun `should return report with errors`() {
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
        val input = OutsideObject(
            total = 6,
            innerList = listOf(
                InternalObject(
                    id = 1,
                    isSuccess = true,
                    errors = listOf()
                ),
                InternalObject(
                    id = 2,
                    isSuccess = false,
                    errors = listOf("error1", "error2")
                ),
                InternalObject(
                    id = 3,
                    isSuccess = false,
                    errors = listOf("error3", "error2")
                ),
                InternalObject(
                    id = 4,
                    isSuccess = false,
                    errors = listOf("error2", "error3")
                ),
                InternalObject(
                    id = 5,
                    isSuccess = true,
                    errors = listOf()
                ),
                InternalObject(
                    id = 6,
                    isSuccess = false,
                    errors = listOf("error2")
                ),
            )
        )

        val reportService = ReportService()

        val report = reportService.getReport(input)

        assertEquals(expected, report)
    }

    @Test
    fun `should return report without errors`() {
        val expected =
            """
            | Report POC
            | Total: 2
            | Inner List Count: 2
            | Success Count: 2
            | Failure Count: 0
            | Errors Report without IDS:
            | 
            | Errors Report with IDS:
            | 
            """.trimMargin()
        val input = OutsideObject(
            total = 2,
            innerList = listOf(
                InternalObject(
                    id = 1,
                    isSuccess = true,
                    errors = listOf()
                ),
                InternalObject(
                    id = 2,
                    isSuccess = true,
                    errors = listOf()
                ),
            )
        )

        val reportService = ReportService()

        val report = reportService.getReport(input)

        assertEquals(expected, report)
    }
}