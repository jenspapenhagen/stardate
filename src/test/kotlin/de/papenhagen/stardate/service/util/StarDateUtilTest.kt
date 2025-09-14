package de.papenhagen.stardate.service.util

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.Clock
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

class StarDateUtilTest {
    private var clock: Clock? = null
    private lateinit var storedLocale: Locale

    @BeforeEach
    fun setUp() {
        // set local for period/comma rules
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
        // given
        val now = LocalDateTime.now(clock)

        // when
        val calcStarDate = StarDateUtil.calc(now)

        // then
        assertThat(calcStarDate).isNotBlank
        assertThat(calcStarDate).isEqualTo("-238297,9")
    }

    @Test
    fun originalSeriesStart() {
        // given
        val tosDate = LocalDateTime.of(2266, 1, 1, 0, 0)

        // when
        val calcStarDate = StarDateUtil.calc(tosDate)

        // then
        assertThat(calcStarDate).isNotBlank
        assertThat(calcStarDate).isEqualTo("2000,0")
    }
}
