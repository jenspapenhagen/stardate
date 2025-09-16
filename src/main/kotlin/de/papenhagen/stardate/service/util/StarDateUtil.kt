package de.papenhagen.stardate.service.util

import java.time.LocalDateTime

/**
 * This Util is calculated the stardate base on the TOS start
 */
open class StarDateUtil {
    companion object {
        private const val TOS_BASE_YEAR: Int = 2265 // Year TOS starts
        private const val UNITS_PER_YEAR: Double = 1000.0

        fun calc(dateTime: LocalDateTime): String {
            val year = dateTime.year
            val dayOfYear = dateTime.dayOfYear
            val hour = dateTime.hour
            val minute = dateTime.minute
            val second = dateTime.second

            val secondsInDay = 86400.0
            val secondsToday = (hour * 3600 + minute * 60 + second)
            val fractionalDay = secondsToday / secondsInDay
            val fractionalYear: Double = (dayOfYear - 1 + fractionalDay) / 365.25

            val stardate: Double = (year - TOS_BASE_YEAR + fractionalYear) * UNITS_PER_YEAR + 1000.0

            return String.format("%.1f", stardate)
        }
    }
}
