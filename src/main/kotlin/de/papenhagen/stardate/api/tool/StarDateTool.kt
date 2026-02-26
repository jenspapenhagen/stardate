package de.papenhagen.stardate.api.tool

import de.papenhagen.stardate.service.StarDate
import de.papenhagen.stardate.service.StarDateService
import org.springaicommunity.mcp.annotation.McpTool
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class StarDateTool(
    private val starDateService: StarDateService,
) {
    @McpTool(
        name = "get-stardate",
        description = "Get the current Star Trek (R) Future Stardate",
    )
    fun getStarDate(): StarDate = starDateService.calcStarDate()

    @McpTool(
        name = "convert-date-to-stardate",
        description = "Convert date/time to Star Trek (R) Future Stardate. Input: YYYY-MM-DD or YYYY-MM-DDTHH:MM:SS",
    )
    fun convertDateToStardate(dateTimeInput: String): StarDate {
        val dateTime = parseDateTimeInput(dateTimeInput)
        return starDateService.calcStarDateForDate(dateTime)
    }

    @McpTool(
        name = "get-stardate-info",
        description = "Get information about Stardates and how they are calculated",
    )
    fun getStardateInfo(): String =
        """
            |Star Trek Future Stardate Information:
            |
            |Base Date: January 1, 2323 (Stardate 0)
            |Formula: (year - 2323 + fractionalYear) * 1000
            |
            |This stardate system is based on the calculation used by the German Star Trek fan community.
            |The base date January 1, 2323 corresponds to Stardate 0.
            |
            |Examples:
            |- Stardate 0.0 = January 1, 2323
            |- Stardate 1000.0 = January 1, 2324
            |- Stardate 10000.0 = January 1, 2333
            |- Stardate 73000.0 = January 1, 2396
            |
            |This tool uses the Future Stardate calculation method:
            |- Year relative to base year 2323
            |- Day of year (0-365)
            |- Time of day as fractional day
        """.trimMargin()

    private fun parseDateTimeInput(input: String): LocalDateTime =
        try {
            if (input.contains("T")) {
                LocalDateTime.parse(input)
            } else {
                LocalDateTime.parse("${input}T00:00:00")
            }
        } catch (e: Exception) {
            throw IllegalArgumentException("Invalid date format. Please use YYYY-MM-DD or YYYY-MM-DDTHH:MM:SS")
        }
}
