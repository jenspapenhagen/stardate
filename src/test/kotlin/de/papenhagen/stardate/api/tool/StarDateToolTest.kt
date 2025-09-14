package de.papenhagen.stardate.api.tool

import de.papenhagen.stardate.service.StarDate
import de.papenhagen.stardate.service.StarDateService
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class StarDateToolTest {
    @Test
    fun getStarDate() {
        // given
        val localDateTime = LocalDateTime.of(2025, 9, 14, 10, 17, 25)
        val service = mockk<StarDateService>()
        every { service.calcStarDate() } returns StarDate(localDateTime, "-238297,9")

        // when
        val tool: StarDateTool = StarDateTool(service)
        val starDate = tool.getStarDate()

        // then
        assertThat(starDate).isNotBlank
        assertThat(starDate).isEqualTo("StarDate(localDateTime=2025-09-14T10:17:25, starDate=-238297,9)")
    }
}
