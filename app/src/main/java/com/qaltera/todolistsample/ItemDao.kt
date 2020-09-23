package com.qaltera.todolistsample

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/*
 * ************************************************
 * ItemDao
 * Date: 2020-09-16
 * Author: Yulia Rogovaya
 * ************************************************
 */

@Dao
interface ItemDao {

    @Insert
    fun insert(item: Item)

    @Update
    fun update(item: Item)

    @Delete
    fun delete(item: Item)

    @Query("DELETE FROM item_table")
    fun deleteAllItems()

    @Query("SELECT * FROM item_table ORDER BY priority DESC")
    fun allItems(): LiveData<List<Item>>
}