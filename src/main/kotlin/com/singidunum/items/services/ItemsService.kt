package com.singidunum.items.services

import com.singidunum.items.models.Item
import com.singidunum.items.repositories.ItemsRepository
import com.singidunum.items.utils.toUUID
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.springframework.stereotype.Service

@Service
class ItemsService(
    private val itemsRepository: ItemsRepository
) {
    fun getAllItems(): List<Item> =
        itemsRepository.getAllItems()

    fun getItemById(itemId: String): Item =
        itemsRepository.getItemById(itemId.toUUID())

    fun insertItem(itemsBody: JsonObject): Item {
        val itemName = itemsBody["itemName"]?.jsonPrimitive?.content
            ?: throw RequestBodyValidationException("itemName is missing")

        return itemsRepository.insertItem(
            itemName = itemName
        )
    }

    fun updateItem(itemId: String, itemsBody: JsonObject): Item {
        val itemName = requireNotNull(itemsBody["itemName"]?.jsonPrimitive?.content)

        return itemsRepository.updateItem(
            itemId = itemId.toUUID(),
            itemName = itemName
        )
    }

    fun deleteItem(itemId: String) =
        itemsRepository.deleteItem(itemId.toUUID())
}