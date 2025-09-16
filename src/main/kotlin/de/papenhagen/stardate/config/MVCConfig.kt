package de.papenhagen.stardate.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.text.SimpleDateFormat

@Configuration
class MVCConfig : WebMvcConfigurer {
    /**
     * This is the default jackson object mapper from Spring.
     * Needed for having a constant object mapper
     * or Spring MVC will load a jackson object mapper out of the class path
     * that can be not the right one.
     *
     * @return a Jackson2ObjectMapperBuilder instance
     */
    @Bean
    fun jackson2ObjectMapperBuilder(): Jackson2ObjectMapperBuilder =
        Jackson2ObjectMapperBuilder()
            .featuresToEnable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES)
            .featuresToEnable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .modulesToInstall(ParameterNamesModule(), JavaTimeModule())
            .dateFormat(SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"))

    @Bean
    fun objectMapper(): ObjectMapper {
        val kotlinModule =
            KotlinModule
                .Builder()
                .withReflectionCacheSize(512)
                .configure(KotlinFeature.NullToEmptyCollection, false)
                .configure(KotlinFeature.NullToEmptyMap, false)
                .configure(KotlinFeature.NullIsSameAsDefault, false)
                .configure(KotlinFeature.SingletonSupport, false)
                .build()

        val timeModule = JavaTimeModule()
        return ObjectMapper()
            .registerModules(kotlinModule, timeModule)
            .configure(SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS, false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE)
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
    }
}
