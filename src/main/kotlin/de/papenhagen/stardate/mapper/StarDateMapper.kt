package de.papenhagen.stardate.mapper

import de.papenhagen.stardate.api.controller.dto.StarDateDto
import de.papenhagen.stardate.service.StarDate
import org.mapstruct.Mapper

@Mapper
interface StarDateMapper {
    fun toDto(starDate: StarDate): StarDateDto

    fun of(starDateDto: StarDateDto): StarDate
}
