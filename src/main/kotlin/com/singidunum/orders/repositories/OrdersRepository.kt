package com.singidunum.orders.repositories

import com.singidunum.orders.database.tables.OrdersTable
import com.singidunum.orders.models.Order
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
class OrdersRepository {
    fun getAllOrders(): List<Order> =
        transaction {
            OrdersTable
                .selectAll()
                .map { row ->
                    Order(
                        id = row[OrdersTable.id].value.toString(),
                        userId = row[OrdersTable.userId].value.toString(),
                        itemId = row[OrdersTable.itemId].value.toString(),
                    )
                }
        }

    fun getOrderById(itemId: UUID): Order {
        val row = transaction {
            OrdersTable.select {
                OrdersTable.id.eq(itemId)
            }.first()
        }

        return Order(
            id = row[OrdersTable.id].value.toString(),
            userId = row[OrdersTable.userId].value.toString(),
            itemId = row[OrdersTable.itemId].value.toString(),
        )
    }

    fun insertOrder(
        userId: UUID,
        itemId: UUID
    ): Order {
        val row = transaction {
            OrdersTable.insert {
                it[OrdersTable.userId] = userId
                it[OrdersTable.itemId] = itemId
            }
        }

        return Order(
            id = row[OrdersTable.id].value.toString(),
            userId = row[OrdersTable.userId].value.toString(),
            itemId = row[OrdersTable.itemId].value.toString(),
        )
    }

    fun updateOrder(
        id: UUID,
        userId: UUID?,
        itemId: UUID?
    ): Order {
        transaction {
            OrdersTable.update(
                where = {
                    OrdersTable.id.eq(itemId)
                },
                body = { statement ->
                    userId?.let { statement[OrdersTable.userId] = userId }
                    itemId?.let { statement[OrdersTable.itemId] = itemId }
                }
            )
        }

        return getOrderById(id)
    }

    fun deleteOrder(id: UUID) =
        transaction {
            OrdersTable.deleteWhere {
                OrdersTable.id.eq(id)
            }
        }
}

