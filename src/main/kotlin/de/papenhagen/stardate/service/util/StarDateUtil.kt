package de.papenhagen.stardate.service.util

import java.time.LocalDateTime

/**
 * This Util calculates the stardate based on the Future Stardate system
 * Base: January 1, 2323 = Stardate 0
 * Formula: (year - 2323 + fractionalYear) * 1000
 */
open class StarDateUtil {
    companion object {
        private const val BASE_YEAR: Int = 2323 // January 1, 2323 = Stardate 0
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

            val stardate: Double = (year - BASE_YEAR + fractionalYear) * UNITS_PER_YEAR

            return String.format("%.1f", stardate)
        }
    }
}
