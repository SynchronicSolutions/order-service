package com.singidunum.items.database

import org.jetbrains.exposed.sql.Database
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DatabaseConfiguration {
    @Bean
    open fun database(): Database =
        DatabaseConnection().connect()
}