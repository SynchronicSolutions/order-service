package com.singidunum.orders.controllers

import com.singidunum.orders.models.Order
import com.singidunum.orders.services.OrdersService
import kotlinx.serialization.json.JsonObject
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("orders")
class OrdersController(
    private val ordersService: OrdersService
) {
    @GetMapping
    fun getOrers(): List<Order> =
        ordersService.getAllOrders()

    @GetMapping("{id}")
    fun getOderById(@PathVariable id: String): Order =
        ordersService.getOrderById(id)

    @PostMapping
    fun createOrder(@RequestBody body: JsonObject): Order =
        ordersService.insertOrder(body)

    @PutMapping("{id}")
    fun updateOrder(@PathVariable id: String, @RequestBody body: JsonObject): Order =
        ordersService.updateOrder(id, body)

    @DeleteMapping("{id}")
    fun deleteOrder(@PathVariable id: String) =
        ordersService.deleteOrder(id)
}

