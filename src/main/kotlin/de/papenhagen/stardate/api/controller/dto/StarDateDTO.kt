package de.papenhagen.stardate.api.controller.dto

import de.papenhagen.stardate.service.StarDate

class StarDateDTO(
    val localDateTime: String,
    val starDate: String,
) {
    companion object {
        fun toDTO(starDate: StarDate): StarDateDTO = StarDateDTO(starDate.localDateTime.toString(), starDate.starDate)
    }
}
