package com.singidunum.users.database.tables

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table.Dual.entityId
import org.jetbrains.exposed.sql.Table.Dual.varchar
import java.util.UUID

object UsersTable: IdTable<UUID>("users") {
    override val id: Column<EntityID<UUID>> = uuid("id").entityId()

    val email = text("email")
    val password = text("password")
    val firstName = text("first_name")
    val lastName = text("last_name")

    override val primaryKey = PrimaryKey(id, name = "PK_users")


}
