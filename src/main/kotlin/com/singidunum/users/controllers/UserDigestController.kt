package com.singidunum.users.controllers

import com.singidunum.users.models.UserDigest
import com.singidunum.users.services.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UserDigestController(
    private val userService: UserService
) {
    @GetMapping("user-digest")
    fun getUserDigest(@RequestParam email: String): UserDigest =
        userService.getUserDigest(email)
}