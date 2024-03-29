package com.singidunum.items.database.tables

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import java.util.UUID

object ItemsTable: IdTable<UUID>("items") {
    override val id: Column<EntityID<UUID>> = uuid("id").entityId()

    val itemName = text("itemName")

    override val primaryKey = PrimaryKey(id, name = "PK_users")
}
