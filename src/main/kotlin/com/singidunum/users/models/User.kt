package com.singidunum.users.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class User(
    val id: String,
    val email: String,
    val firstName: String,
    val lastName: String,
)