# Stardate Claude Plugin

A Claude Code plugin that provides Star Trek Future Stardate calculations and conversions via MCP (Model Context Protocol).

## Features

- **Get Current Stardate**: Get the current stardate in Future format
- **Convert Dates**: Convert any date/time to stardate format  
- **Get Information**: Learn about how Future stardates are calculated

## Requirements

- Java 21 or higher
- Maven 3.8+
- Claude Code 1.0.33 or higher

## Installation

### Option 1: Local Development

1. Clone this repository:
   ```bash
   git clone https://github.com/papenhagen/stardate.git
   cd stardate
   ```

2. Build the JAR:
   ```bash
   ./mvnw clean package -DskipTests
   ```

3. Copy the JAR to the plugin directory:
   ```bash
   cp target/stardate-*.jar stardate-plugin/stardate.jar
   ```

4. Load the plugin in Claude Code:
   ```bash
   claude --plugin-dir ./stardate-plugin
   ```

### Option 2: From Release

1. Download the latest release JAR from the releases page
2. Place the JAR in the `stardate-plugin/` directory
3. Install via Claude Code plugin manager

## Usage

Once installed, you can use the following commands:

### Get Current Stardate
```
/stardate:get-stardate
```

Returns the current stardate in Future format, for example:
```
StarDate(localDateTime=2026-02-26T14:30:00, starDate=73000.0)
```

### Convert Date to Stardate
```
/stardate:convert-date-to-stardate 2026-01-15
```

Or with time:
```
/stardate:convert-date-to-stardate 2026-06-20T14:30:45
```

### Get Stardate Information
```
/stardate:get-stardate-info
```

Learn about how Future stardates are calculated and their history.

## MCP Tools

This plugin exposes the following MCP tools:

| Tool | Description |
|------|-------------|
| `get-stardate` | Get the current Star Trek (R) Future Stardate |
| `convert-date-to-stardate` | Convert a specific date/time to Star Trek (R) Future Stardate format |
| `get-stardate-info` | Get information about Stardates and how they are calculated |

## Stardate Format

The Future Stardate system is based on the calculation used by the German Star Trek fan community.

**Formula:**
```
(year - 2323 + fractionalYear) * 1000
```

Where:
- **Base Date**: January 1, 2323 (Stardate 0)
- **Fractional Year**: Calculated from day of year and time of day

**Examples:**
- Stardate 0.0 = January 1, 2323
- Stardate 1000.0 = January 1, 2324
- Stardate 10000.0 = January 1, 2333
- Stardate 73000.0 = January 1, 2396

## Development

### Building

```bash
./mvnw clean package
```

### Testing

```bash
./mvnw test
```

### Running Standalone

The application can also run as a standalone Spring Boot service:

```bash
java -jar target/stardate-*.jar
```

The REST API will be available at `http://localhost:8080/date`

## Project Structure

```
stardate/
├── stardate-plugin/
│   ├── .claude-plugin/
│   │   └── plugin.json          # Plugin manifest
│   ├── .mcp.json                # MCP server configuration
│   └── stardate.jar             # Built JAR (after running build)
├── src/
│   ├── main/kotlin/
│   │   └── de/papenhagen/stardate/
│   │       ├── api/
│   │       │   ├── controller/  # REST API endpoints
│   │       │   └── tool/         # MCP tools
│   │       ├── config/           # Spring configuration
│   │       └── service/          # Business logic
│   └── test/                    # Unit tests
├── pom.xml                      # Maven configuration
└── README.md                    # This file
```

## License

MIT License - See LICENSE file for details

## Acknowledgments

- Star Trek is a registered trademark of Paramount Pictures
- Based on the Future Stardate calculation from the German Star Trek fan community
- Built with Spring AI MCP Server
