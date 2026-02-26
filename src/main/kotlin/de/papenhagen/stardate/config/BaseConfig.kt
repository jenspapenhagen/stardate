package de.papenhagen.stardate.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Clock
import java.time.ZoneId

@Configuration
class BaseConfig {
    @Bean
    fun clock(): Clock = Clock.system(ZoneId.of("Europe/Berlin"))
}
