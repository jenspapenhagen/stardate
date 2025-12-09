package de.papenhagen.stardate.config

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import tools.jackson.databind.ObjectMapper
import tools.jackson.databind.json.JsonMapper
import java.util.*
import java.util.function.UnaryOperator

@Configuration
class MVCConfig : WebMvcConfigurer {
    @Bean
    fun objectMapper(): ObjectMapper =
        JsonMapper
            .builder()
            .changeDefaultPropertyInclusion(
                UnaryOperator { incl: JsonInclude.Value? ->
                    incl!!.withValueInclusion(
                        JsonInclude.Include.ALWAYS,
                    )
                },
            ).defaultTimeZone(TimeZone.getTimeZone("UTC")) // set a default timezone for dates
            .build()
}
