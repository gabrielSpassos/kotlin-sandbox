package com.gabrielspassos

import kotlin.math.ceil

object MyMath {

    fun divideIntRound(value: Long, divisor: Long): Long {
        val result = value.toDouble() / divisor.toDouble()
        return ceil(result).toLong()
    }

    fun splitGroups(group: Int): Pair<Long, Long> {
        val totalPages = 50
        val pagesToProcess = divideIntRound(totalPages.toLong(), 5)

        val firstPage = if (1 == group) {
            1
        } else {
            val temp = group - 1
            (pagesToProcess * temp) + 1
        }
        val lastPage = pagesToProcess * group

        println("Total pages: $totalPages")
        println("PagesToProcess: $pagesToProcess")
        println("First page: $firstPage LastPage: $lastPage")

        return Pair(firstPage, lastPage)
    }
}