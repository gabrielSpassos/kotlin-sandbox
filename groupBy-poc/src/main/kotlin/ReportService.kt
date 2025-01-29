package com.gabrielspassos

class ReportService {

    fun getReport(report: OutsideObject): String {
        val innerListPartition = report.innerList.partition { it.isSuccess }
        val successCases = innerListPartition.first
        val failureCases = innerListPartition.second

        val errorsReportWithoutIds= failureCases
            .asSequence()
            .flatMap { it.errors }
            .groupBy { it }
            .map { (errorName, errors) -> errorName to errors.size }
            .sortedByDescending { (_, errorCount) -> errorCount }
            .joinToString(separator = "\n ") { (errorName, errorCount) -> "Error: $errorName | Count: $errorCount" }

        val errorsReportWithIds = failureCases
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
            | Errors Report without IDS:
            | $errorsReportWithoutIds
            | Errors Report with IDS:
            | $errorsReportWithIds
        """.trimMargin()

        return report
    }

}