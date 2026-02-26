package de.papenhagen.stardate.service.util

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.Clock
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Locale

class StarDateUtilTest {
    private var clock: Clock? = null
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
    fun testingStarDateGeneration() {
        val now = LocalDateTime.now(clock)

        val calcStarDate = StarDateUtil.calc(now)

        assertThat(calcStarDate).isNotBlank
        assertThat(calcStarDate).isEqualTo("-297297,9")
    }

    @Test
    fun originalSeriesStart() {
        val baseDate = LocalDateTime.of(2323, 1, 1, 0, 0)

        val calcStarDate = StarDateUtil.calc(baseDate)

        assertThat(calcStarDate).isNotBlank
        assertThat(calcStarDate).isEqualTo("0,0")
    }

    @Test
    fun testForCoveringTheConstructor() {
        val tosDate = LocalDateTime.of(2323, 1, 1, 0, 0)

        val starDateUtil: StarDateUtil = StarDateUtil()
        val toString = starDateUtil.toString()

        assertThat(toString).isNotBlank
        assertThat(toString).contains("service.util.StarDateUtil")
    }
}
