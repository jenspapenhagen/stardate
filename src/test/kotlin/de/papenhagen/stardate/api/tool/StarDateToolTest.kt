package de.papenhagen.stardate.api.tool

import de.papenhagen.stardate.service.StarDate
import de.papenhagen.stardate.service.StarDateService
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class StarDateToolTest {
    private val starDateService: StarDateService = mockk<StarDateService>()
    private lateinit var starDateTool: StarDateTool

    @BeforeEach
    fun setup() {
        clearAllMocks()
        starDateTool = StarDateTool(starDateService)
    }

    @Test
    fun `should return stardate when service returns value`() {
        val localDateTime = LocalDateTime.of(2025, 9, 14, 10, 17, 25)
        every { starDateService.calcStarDate() } returns StarDate(localDateTime, "2265.5")

        val result = starDateTool.getStarDate()

        assertThat(result.localDateTime).isEqualTo(localDateTime)
        assertThat(result.starDate).isEqualTo("2265.5")
    }

    @Test
    fun `should convert date to stardate when valid date provided`() {
        val localDateTime = LocalDateTime.of(2025, 1, 15, 0, 0, 0)
        every { starDateService.calcStarDateForDate(any()) } returns StarDate(localDateTime, "2260.4")

        val result = starDateTool.convertDateToStardate("2025-01-15")

        assertThat(result.localDateTime).isEqualTo(localDateTime)
        assertThat(result.starDate).isEqualTo("2260.4")
    }

    @Test
    fun `should convert datetime to stardate when datetime with time provided`() {
        val localDateTime = LocalDateTime.of(2025, 6, 20, 14, 30, 45)
        every { starDateService.calcStarDateForDate(any()) } returns StarDate(localDateTime, "2265.0")

        val result = starDateTool.convertDateToStardate("2025-06-20T14:30:45")

        assertThat(result.localDateTime).isEqualTo(localDateTime)
        assertThat(result.starDate).isEqualTo("2265.0")
    }

    @Test
    fun `should throw exception when invalid date format provided`() {
        assertThatThrownBy { starDateTool.convertDateToStardate("invalid-date") }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContaining("Invalid date format")
    }

    @Test
    fun `should return stardate info when getStardateInfo called`() {
        val result = starDateTool.getStardateInfo()

        assertThat(result).contains("Star Trek Future Stardate")
        assertThat(result).contains("Base Date: January 1, 2323")
        assertThat(result).contains("Formula")
    }
}
