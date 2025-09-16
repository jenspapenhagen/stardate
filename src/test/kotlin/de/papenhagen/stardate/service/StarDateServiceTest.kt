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
    fun calcStarDate() {
        // given
        val localDateTime = LocalDateTime.of(2025, 9, 14, 10, 17, 25)
        val expectedStarDate = StarDate(localDateTime, "-238297,9")
        val service: StarDateService = StarDateService(clock)

        // when
        val calcStarDate = service.calcStarDate()

        // then
        assertThat(calcStarDate).isNotNull
        assertThat(calcStarDate).isEqualTo(expectedStarDate)
    }
}
