package com.singidunum.users.services

import com.singidunum.users.models.User
import com.singidunum.users.models.UserDigest
import com.singidunum.users.repositories.UserRepository
import com.singidunum.users.utils.toUUID
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonPrimitive
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun getAllUsers(filterByEmail: String? = null): List<User> =
        userRepository.getAllUsers(filterByEmail)

    fun getUserDigest(email: String): UserDigest =
        userRepository.getUserDigestByEmail(email)

    fun getUserById(userId: String): User =
        userRepository.getUserById(userId.toUUID())

    fun insertUser(userBody: JsonObject): User {
        val email = userBody["email"]?.jsonPrimitive?.content
            ?: throw RequestBodyValidationException("email is missing")

        val password = userBody["password"]?.jsonPrimitive?.content
            ?: throw RequestBodyValidationException("password is missing")

        val firstName = userBody["firstName"]?.jsonPrimitive?.content
            ?: throw RequestBodyValidationException("firstName is missing")

        val lastName = userBody["lastName"]?.jsonPrimitive?.content
            ?: throw RequestBodyValidationException("lastName is missing")

        return userRepository.insertUser(
            email = email,
            password = password,
            firstName = firstName,
            lastName = lastName
        )
    }

    fun updateUser(userId: String, userBody: JsonObject): User {
        val email = userBody["email"]?.jsonPrimitive?.content
        val password = userBody["password"]?.jsonPrimitive?.content
        val firstName = userBody["firstName"]?.jsonPrimitive?.content
        val lastName = userBody["lastName"]?.jsonPrimitive?.content

        return userRepository.updateUser(
            userId = userId.toUUID(),
            email = email,
            password = password,
            firstName = firstName,
            lastName = lastName
        )
    }

    fun deleteUser(userId: String) =
        userRepository.deleteUser(userId.toUUID())
}