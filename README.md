# LocalDateTime to Star Trek (R) Stardate
A  Spring Boot Service for displaying the current LocalDateTime in the star-trek(r) Original Series (TOS) Stardate format


## REST Endpoint
Normal HTTP Endpoint to get the current Star Trek (TOS) style stardate as a JSON
```bash
curl http://localhost:8080/date
```

return a JSON like that:

```json
{
  "localDateTime": "2025-09-14T10:17:25",
  "starDate": "-238297,9"
}
```

## MCP Tool
This MCP tool exposes an endpoint named "get-stardate" that, when called, returns the current Star Trek (TOS) style stardate as a string. It does this by delegating the calculation to a service called StarDateService
```bash
mcp run get-stardate
```

## Technologies in use

- [Maven](https://maven.apache.org/)
- [Kotlin](https://kotlinlang.org/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring AI MCP](https://docs.spring.io/spring-ai/reference/api/mcp/mcp-overview.html)
- [Junit](https://junit.org/)
- [Assertj](https://joel-costigliola.github.io/assertj/)
- [MapStruct](https://mapstruct.org/)
- [Kover (test coverage)](https://github.com/Kotlin/kotlinx-kover)
- [Ktlint](https://github.com/pinterest/ktlint)
- [flatten maven plugin](https://www.mojohaus.org/flatten-maven-plugin/)