package com.qaltera.todolistsample

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
 * ************************************************
 * Note
 * Date: 2020-09-16
 * Author: Yulia Rogovaya
 * ************************************************
 */

@Entity(tableName = "note_table")
class Note(
    val title: String,
    val description: String,
    val importance: Boolean
) {

    @PrimaryKey(autoGenerate = true)
    var id = 0
}