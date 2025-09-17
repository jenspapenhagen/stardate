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