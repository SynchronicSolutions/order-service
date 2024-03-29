package com.singidunum.items

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class UserApplication

fun main(args: Array<String>) {
	runApplication<UserApplication>(*args)
}

@RestController
class TestController {

	@GetMapping("/")
	fun getURL(): JsonObject {
		return JsonObject(
			content = mapOf(
				"user" to JsonPrimitive("userid")
			)
		)
	}
}
