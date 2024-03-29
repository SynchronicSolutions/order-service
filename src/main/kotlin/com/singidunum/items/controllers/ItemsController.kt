package com.singidunum.items.controllers

import com.singidunum.items.models.Item
import com.singidunum.items.services.ItemService
import com.singidunum.items.services.ItemsService
import kotlinx.serialization.json.JsonObject
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("items")
class ItemsController(
    private val itemsService: ItemsService
) {
    @GetMapping
    fun getItems(): List<Item> =
        itemsService.getAllItems()

    @GetMapping("{itemsId}")
    fun getItemById(@PathVariable itemsId: String): Item =
        itemsService.getItemById(itemsId)

    @PostMapping
    fun createItem(@RequestBody body: JsonObject): Item =
        itemsService.insertItem(body)

    @PutMapping("{itemsId}")
    fun updateItem(@PathVariable itemsId: String, @RequestBody body: JsonObject): Item =
        itemsService.updateItem(itemsId, body)

    @DeleteMapping("{itemsId}")
    fun deleteItem(@PathVariable itemsId: String) =
        itemsService.deleteItem(itemsId)
}

