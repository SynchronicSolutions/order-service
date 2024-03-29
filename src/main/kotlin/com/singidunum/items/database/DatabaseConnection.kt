package com.singidunum.items.database

import org.jetbrains.exposed.sql.Database

class DatabaseConnection {
    fun connect(): Database =
        Database.connect(
            url = "jdbc:postgresql://localhost:5432/items_db",
            driver = "org.postgresql.Driver",
            user = "items_admin",
            password = "items_admin"
        )
}