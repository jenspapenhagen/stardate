package de.papenhagen.stardate.api.controller

import de.papenhagen.stardate.service.StarDate
import de.papenhagen.stardate.service.StarDateService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
@WebMvcTest(InboundController::class)
class InboundControllerTest {
    @TestConfiguration
    class ControllerTestConfig {
        @Bean
        fun service() = mockk<StarDateService>()
    }

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var service: StarDateService

    @Test
    fun `test get called`() {
        // given
        val localDateTime = LocalDateTime.of(2025, 9, 14, 10, 17, 25)
        val expectedMessage = StarDate(localDateTime, "-238297,9")
        every { service.calcStarDate() } returns expectedMessage

        // when
        val result =
            mockMvc
                .perform(get("/date"))
                .andExpect(status().isOk)
                .andDo(print())
                .andReturn()

        // then
        assertThat(result.response.contentAsString).isEqualTo("{\"localDateTime\":\"2025-09-14T10:17:25\",\"starDate\":\"-238297,9\"}")
        verify { service.calcStarDate() }
    }
}
