package com.singidunum.users.database

import org.jetbrains.exposed.sql.Database
import org.springframework.context.annotation.Configuration

class DatabaseConnection {
    fun connect(): Database =
        Database.connect(
            url = "jdbc:postgresql://localhost:5432/users_db",
            driver = "org.postgresql.Driver",
            user = "users_admin",
            password = "users_admin"
        )
}