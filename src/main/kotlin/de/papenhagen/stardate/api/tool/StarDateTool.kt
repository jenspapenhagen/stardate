package de.papenhagen.stardate.api.tool

import de.papenhagen.stardate.service.StarDateService
import org.springaicommunity.mcp.annotation.McpTool
import org.springframework.stereotype.Component

/**
 * MCP tools for getting local time in the Star Trek (R) TOS StarDate Format
 */
@Component
class StarDateTool(
    private val starDateService: StarDateService,
) {
    @McpTool(
        name = "get-stardate",
        description = "Get the Star Trek (R) Stardate from the TOS era",
    )
    fun getStarDate(): String {
        val calcStarDate = starDateService.calcStarDate()
        return calcStarDate.toString()
    }
}
