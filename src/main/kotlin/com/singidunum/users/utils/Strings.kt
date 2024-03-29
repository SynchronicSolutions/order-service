package com.singidunum.users.utils

import java.util.*

fun String.toUUID(): UUID = UUID.fromString(this)