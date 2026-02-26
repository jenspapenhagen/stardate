package de.papenhagen.stardate.api.controller

import de.papenhagen.stardate.api.controller.dto.StarDateDto
import de.papenhagen.stardate.mapper.StarDateMapper
import de.papenhagen.stardate.service.StarDateService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class InboundController(
    private val starDateService: StarDateService,
    private val starDateMapper: StarDateMapper,
) {
    @GetMapping("/date", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getStarDate(): StarDateDto {
        val starDate = starDateService.calcStarDate()
        return starDateMapper.toDto(starDate)
    }
}
