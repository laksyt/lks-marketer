package com.github.laksyt.marketer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MarketerApplication

fun main(args: Array<String>) {
	runApplication<MarketerApplication>(*args) {
		setDefaultProperties(mapOf("server.port" to "8777"))
	}
}
