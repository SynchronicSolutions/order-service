package com.singidunum.users.models

import kotlinx.serialization.Serializable

@Serializable
data class UserDigest(
    val email: String,
    val passwordHash: String
)