package de.papenhagen.stardate.api.controller

import de.papenhagen.stardate.api.controller.dto.StarDateDto
import de.papenhagen.stardate.mapper.StarDateMapper
import de.papenhagen.stardate.service.StarDate
import de.papenhagen.stardate.service.StarDateService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class InboundControllerTest {
    @Test
    fun `test get called`() {
        // given
        val localDateTime = LocalDateTime.of(2025, 9, 14, 10, 17, 25)
        val starDate = "-238297,9"
        val expectedMessage = StarDate(localDateTime, starDate)

        val service: StarDateService = mockk<StarDateService>()
        every { service.calcStarDate() } returns expectedMessage

        val starDateMapper: StarDateMapper = mockk<StarDateMapper>()
        every { starDateMapper.toDto(any<StarDate>()) } returns StarDateDto(localDateTime.toString(), starDate)

        val controller: InboundController = InboundController(service, starDateMapper)

        // when
        val result = controller.getStarDate()

        // then
        assertThat(result.toString()).isEqualTo("StarDateDto(localDateTime=2025-09-14T10:17:25, starDate=-238297,9)")
        verify { service.calcStarDate() }
        verify { starDateMapper.toDto(any<StarDate>()) }
    }
}
