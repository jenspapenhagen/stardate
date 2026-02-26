package de.papenhagen.stardate.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.Clock
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Locale

class StarDateServiceTest {
    private lateinit var clock: Clock
    private lateinit var storedLocale: Locale

    @BeforeEach
    fun setUp() {
        storedLocale = Locale.GERMAN

        val instantExpected = "2025-09-14T10:17:25Z"
        clock = Clock.fixed(Instant.parse(instantExpected), ZoneId.of("UTC"))
    }

    @AfterEach
    fun teardown() {
        Locale.setDefault(storedLocale)
    }

    @Test
    fun calcStarDate() {
        val localDateTime = LocalDateTime.of(2025, 9, 14, 10, 17, 25)
        val expectedStarDate = StarDate(localDateTime, "-297297,9")

        val starDateService = StarDateService(clock)
        val calcStarDate = starDateService.calcStarDate()

        assertThat(calcStarDate).isNotNull

        assertThat(calcStarDate.starDate).startsWith("-297297")
        assertThat(calcStarDate.starDate).endsWith("9")

        assertThat(calcStarDate.localDateTime.year).isEqualTo(expectedStarDate.localDateTime.year)
        assertThat(calcStarDate.localDateTime.dayOfYear).isEqualTo(expectedStarDate.localDateTime.dayOfYear)
        assertThat(calcStarDate.localDateTime.month).isEqualTo(expectedStarDate.localDateTime.month)
    }

    @Test
    fun calcStarDateForDate() {
        val localDateTime = LocalDateTime.of(2026, 1, 1, 0, 0, 0)

        val starDateService = StarDateService(clock)
        val calcStarDate = starDateService.calcStarDateForDate(localDateTime)

        assertThat(calcStarDate).isNotNull
        assertThat(calcStarDate.localDateTime).isEqualTo(localDateTime)
        assertThat(calcStarDate.starDate).isNotBlank
    }

    @Test
    fun calcStarDateForDateWithTime() {
        val localDateTime = LocalDateTime.of(2025, 6, 15, 12, 30, 45)

        val starDateService = StarDateService(clock)
        val calcStarDate = starDateService.calcStarDateForDate(localDateTime)

        assertThat(calcStarDate).isNotNull
        assertThat(calcStarDate.localDateTime).isEqualTo(localDateTime)
        assertThat(calcStarDate.starDate).isNotBlank
    }
}
