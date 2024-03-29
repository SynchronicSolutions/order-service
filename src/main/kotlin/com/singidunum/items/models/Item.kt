package com.singidunum.items.models

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val id: String,
    val itemName: String
)