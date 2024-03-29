package com.singidunum.orders.models

import kotlinx.serialization.Serializable

@Serializable
data class Order(
    val id: String,
    val userId: String,
    val itemId: String
)