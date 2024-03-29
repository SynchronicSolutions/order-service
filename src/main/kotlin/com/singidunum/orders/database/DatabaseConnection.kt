package com.singidunum.orders.database

import org.jetbrains.exposed.sql.Database

class DatabaseConnection {
    fun connect(): Database =
        Database.connect(
            url = "jdbc:postgresql://localhost:5432/orders_db",
            driver = "org.postgresql.Driver",
            user = "orders_admin",
            password = "orders_admin"
        )
}