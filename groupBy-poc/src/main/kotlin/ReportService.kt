package com.gabrielspassos

import java.util.UUID

class ReportService {

    private val report = OutsideObject(
        total = 5,
        innerList = listOf(
            InternalObject(
                id = UUID.randomUUID(),
                isSuccess = true,
                errors = listOf()
            ),
            InternalObject(
                id = UUID.randomUUID(),
                isSuccess = false,
                errors = listOf("error1", "error2")
            ),
            InternalObject(
                id = UUID.randomUUID(),
                isSuccess = false,
                errors = listOf("error3", "error2")
            ),
            InternalObject(
                id = UUID.randomUUID(),
                isSuccess = false,
                errors = listOf("error2", "error3")
            ),
            InternalObject(
                id = UUID.randomUUID(),
                isSuccess = true,
                errors = listOf()
            ),
        )
    )

    fun getReport(): String {
        val innerListPartition = report.innerList.partition { it.isSuccess }
        val successCases = innerListPartition.first
        val failureCases = innerListPartition.second

        val errorsReport = failureCases
            .flatMap { it.errors }
            .groupBy { it }
            .map { (key, value) -> key to value.size }
            .joinToString(separator = "\n ") { (key, value) -> "Error: $key | Count: $value" }

        val report = """
            | Report POC
            | Total: ${report.total}
            | Inner List Count: ${report.innerList.size}
            | Success Count: ${successCases.size}
            | Failure Count: ${failureCases.size}
            | Errors Report:
            | $errorsReport
        """.trimMargin()

        return report
    }
}