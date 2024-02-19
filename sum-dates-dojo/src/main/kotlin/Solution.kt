package com.gabrielspassos

import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

class Solution {

    companion object {
        val DATE_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    }

    fun calculateDateWithLib(dateTime: LocalDateTime, seconds: Long): LocalDateTime {
        return dateTime.plusSeconds(seconds)
    }

    fun calculateDate(dateTime: String, plusSeconds: Long): String {
        val splitDate = dateTime.split(" ")
        val slicedDate = splitDate[0].split("-")
        val slicedTime = splitDate[1].split(":")

        val year = slicedDate[0].toInt()
        val month = slicedDate[1].toInt()
        val day = slicedDate[2].toInt()

        val hour = slicedTime[0].toInt()
        val minute = slicedTime[1].toInt()
        val second = slicedTime[2].toInt()

        val unitMinute = 60
        val unitHour = 60 * unitMinute
        val unitDay = 24 * unitHour
        val unitMonth = 30 * unitDay
        val unitYear = 12 * unitMonth
        val unitMillennium = 1000 * unitYear

        val nextSeconds = plusSeconds % unitMinute
        val nextMinute = (plusSeconds % unitHour) / unitMinute
        val nextHour = (plusSeconds % unitDay) / unitHour
        val nextDay = (plusSeconds % unitMonth) / unitDay
        val nextMonth = (plusSeconds % unitYear) / unitMonth
        val nextYear = (plusSeconds % unitMillennium) / unitYear

        val finalSec = second + nextSeconds
        val finalMinute = minute + nextMinute
        val finalHour = hour + nextHour
        val finalDay = day + nextDay
        val finalMonth = month + nextMonth
        val finalYear = year + nextYear

        return StringBuilder()
            .append("$finalYear".padStart(4, '0')).append("-")
            .append("$finalMonth".padStart(2, '0')).append("-")
            .append("$finalDay".padStart(2, '0')).append(" ")
            .append("$finalHour".padStart(2, '0')).append(":")
            .append("$finalMinute".padStart(2, '0')).append(":")
            .append("$finalSec".padStart(2, '0'))
            .toString()
    }

}