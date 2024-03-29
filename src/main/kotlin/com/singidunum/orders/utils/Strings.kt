package com.singidunum.orders.utils

import java.util.*

fun String.toUUID(): UUID = UUID.fromString(this)