package com.singidunum.items.repositories

import com.singidunum.items.database.tables.ItemsTable
import com.singidunum.items.models.Item
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class ItemsRepository {
    fun getAllItems(): List<Item> =
        transaction {
            ItemsTable
                .selectAll()
                .map { row ->
                    Item(
                        id = row[ItemsTable.id].value.toString(),
                        itemName = row[ItemsTable.itemName]
                    )
                }
        }

    fun getItemById(itemId: UUID): Item {
        val row = transaction {
            ItemsTable.select {
                ItemsTable.id.eq(itemId)
            }.first()
        }

        return Item(
            id = row[ItemsTable.id].value.toString(),
            itemName = row[ItemsTable.itemName]
        )
    }

    fun insertItem(
        itemName: String
    ): Item {
        val row = transaction {
            ItemsTable.insert {
                it[ItemsTable.itemName] = itemName
            }
        }

        return Item(
            id = row[ItemsTable.id].value.toString(),
            itemName = row[ItemsTable.itemName]
        )
    }

    fun updateItem(
        itemId: UUID,
        itemName: String
    ): Item {
        val row = transaction {
            ItemsTable.update(
                where = {
                    ItemsTable.id.eq(itemId)
                },
                body = { statement ->
                    statement[ItemsTable.itemName] = itemName
                }
            )
        }

        return getItemById(itemId)
    }

    fun deleteItem(itemId: UUID) =
        transaction {
            ItemsTable.deleteWhere {
                ItemsTable.id.eq(itemId)
            }
        }
}

