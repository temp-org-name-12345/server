package com.example.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ServerApplication


fun main(args: Array<String>) {
	val props = System.getProperties()
	val en = props.propertyNames()

	while (en.hasMoreElements()) {
		val key = en.nextElement() as String
		val value = props.getProperty(key)
		println("$key : $value")
	}

	runApplication<ServerApplication>(*args)
}
