package com.singidunum.items.utils

import java.util.*

fun String.toUUID(): UUID = UUID.fromString(this)