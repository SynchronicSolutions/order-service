package com.singidunum.orders.database.tables

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import java.util.UUID

object OrdersTable: IdTable<UUID>("orders") {
    override val id: Column<EntityID<UUID>> = uuid("id").entityId()

    val userId: Column<EntityID<UUID>> = uuid("userId").entityId()
    val itemId: Column<EntityID<UUID>> = uuid("itemId").entityId()
    
    override val primaryKey = PrimaryKey(id, name = "PK_orders")
}
