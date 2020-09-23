package com.qaltera.todolistsample

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "item_table")
class Item(
    val title: String,
    val description: String,
    val priority: Boolean
): Serializable {

    @PrimaryKey(autoGenerate = true)
    var id = 0
}