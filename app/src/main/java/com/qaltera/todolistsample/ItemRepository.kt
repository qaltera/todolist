package com.qaltera.todolistsample

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope

/*
 * ************************************************
 * ItemRepository
 * Date: 2020-09-16
 * Author: Yulia Rogovaya
 * ************************************************
 */

class ItemRepository(
    context: Context,
    scope: CoroutineScope
) {
    private val itemDao: ItemDao
    val allItems: LiveData<List<Item>>

    init {
        val database: ItemDatabase = ItemDatabase.getDatabase(
            context,
            scope
        )
        itemDao = database.itemDao()
        allItems = itemDao.allItems()
    }

    suspend fun insert(item: Item) {
        itemDao.insert(item)
    }

    suspend fun update(item: Item) {
        itemDao.update(item)
    }

    suspend fun delete(item: Item) {
        itemDao.delete(item)
    }

    suspend fun deleteAllItems() {
        itemDao.deleteAllItems()
    }
}