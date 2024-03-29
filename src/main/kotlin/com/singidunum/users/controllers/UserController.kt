package com.singidunum.users.controllers

import com.singidunum.users.models.User
import com.singidunum.users.models.UserDigest
import com.singidunum.users.services.UserService
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("users")
class UserController(
    private val userService: UserService
) {
    @GetMapping
    fun getUsers(@RequestParam userEmail: String?): List<User> =
        userService.getAllUsers(userEmail)

    @GetMapping("{userId}")
    fun getUserById(@PathVariable userId: String): User =
        userService.getUserById(userId)

    @PostMapping
    fun createUser(@RequestBody body: JsonObject): User =
        userService.insertUser(body)

    @PutMapping("{userId}")
    fun updateUser(@PathVariable userId: String, @RequestBody body: JsonObject): User =
        userService.updateUser(userId, body)

    @DeleteMapping("{userId}")
    fun deleteUser(@PathVariable userId: String) =
        userService.deleteUser(userId)
}

