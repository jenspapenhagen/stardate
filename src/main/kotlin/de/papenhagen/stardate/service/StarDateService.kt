package de.papenhagen.stardate.service

import de.papenhagen.stardate.service.util.StarDateUtil
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service
import java.time.Clock
import java.time.LocalDateTime

@Service
class StarDateService(
    val clock: Clock,
) {
    private val logger = KotlinLogging.logger {}

    open fun calcStarDate(): StarDate {
        val now = LocalDateTime.now(clock)
        val starDate = StarDateUtil.calc(now)
        logger.debug { "Stardate $starDate for LocalDate: $now " }

        return StarDate(now, starDate)
    }
}
