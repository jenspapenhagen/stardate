package de.papenhagen.stardate.service

import de.papenhagen.stardate.service.util.StarDateUtil
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import java.time.Clock
import java.time.LocalDateTime

@Component
class StarDateService(
    val clock: Clock,
) {
    private val logger = KotlinLogging.logger {}

    open fun calc(): String {
        val now = LocalDateTime.now(clock)
        val calc = StarDateUtil.calc(now)
        logger.info { "Stardate $calc for LocalDate: $now " }
        return "asdasdasd"
    }
}
