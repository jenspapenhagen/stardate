package de.papenhagen.stardate.api.tool

import de.papenhagen.stardate.service.StarDate
import de.papenhagen.stardate.service.StarDateService
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
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
        // given
        val localDateTime = LocalDateTime.of(2025, 9, 14, 10, 17, 25)
        every { starDateService.calcStarDate() } returns StarDate(localDateTime, "2265.5")

        // when
        val result = starDateTool.getStarDate()

        // then
        assertThat(result).isEqualTo("StarDate(localDateTime=2025-09-14T10:17:25, starDate=2265.5)")
    }

      @Test
    fun getStarDate() {
        // given
        val localDateTime = LocalDateTime.of(2025, 9, 14, 10, 17, 25)
        every { starDateService.calcStarDate() } returns StarDate(localDateTime, "-238297,9")

        // when
        val tool = StarDateTool(starDateService)
        val starDate = tool.getStarDate()

        // then
        assertThat(starDate).isNotBlank
        assertThat(starDate).isEqualTo("StarDate(localDateTime=2025-09-14T10:17:25, starDate=-238297,9)")
    }
}
