package de.papenhagen.stardate.api.controller

import de.papenhagen.stardate.api.controller.dto.StarDateDTO
import de.papenhagen.stardate.service.StarDateService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class InboundController(
    private val starDateService: StarDateService,
) {
    @GetMapping("/date")
    fun getStarDate(): StarDateDTO {
        val starDate = starDateService.calcStarDate()
        return StarDateDTO.toDTO(starDate)
    }
}
