package com.singidunum.orders.services

import com.singidunum.orders.models.Order
import com.singidunum.orders.repositories.OrdersRepository
import com.singidunum.orders.utils.toUUID
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.springframework.stereotype.Service

@Service
class OrdersService(
    private val ordersRepository: OrdersRepository
) {
    fun getAllOrders(): List<Order> =
        ordersRepository.getAllOrders()

    fun getOrderById(id: String): Order =
        ordersRepository.getOrderById(id.toUUID())

    fun insertOrder(orderBody: JsonObject): Order {
        val userId = orderBody["userId"]?.jsonPrimitive?.content
            ?: throw RequestBodyValidationException("itemName is missing")

        val itemId = orderBody["itemId"]?.jsonPrimitive?.content
            ?: throw RequestBodyValidationException("itemId is missing")

        return ordersRepository.insertOrder(
            userId = userId.toUUID(),
            itemId = itemId.toUUID()
        )
    }

    fun updateOrder(id: String, orderBody: JsonObject): Order {
        val userId = orderBody["userId"]?.jsonPrimitive?.content

        val itemId = orderBody["itemId"]?.jsonPrimitive?.content

        return ordersRepository.updateOrder(
            id = id.toUUID(),
            userId = userId?.toUUID(),
            itemId = itemId?.toUUID()
        )
    }

    fun deleteOrder(id: String) =
        ordersRepository.deleteOrder(id.toUUID())
}