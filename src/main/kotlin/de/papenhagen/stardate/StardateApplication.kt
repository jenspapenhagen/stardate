package de.papenhagen.stardate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StardateApplication

fun main(args: Array<String>) {
	runApplication<StardateApplication>(*args)
}
