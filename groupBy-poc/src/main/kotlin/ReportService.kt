package com.gabrielspassos

class ReportService {

    private val report = OutsideObject(
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

    fun getReport(): String {
        val innerListPartition = report.innerList.partition { it.isSuccess }
        val successCases = innerListPartition.first
        val failureCases = innerListPartition.second

        val errorsReport = failureCases
            .flatMap { internalObject -> internalObject.errors.map { error -> error to internalObject.id } }
            .groupBy { (errorName, id) -> errorName }
            .mapValues { (errorName, errorsAndIds) -> errorsAndIds.map { (name, id) -> id } }
            .toList()
            .sortedByDescending { (errorName, ids) -> ids.size }
            .joinToString(separator = "\n ") { (errorName, ids) -> "Error: $errorName | Count: ${ids.size} | Users: $ids" }

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