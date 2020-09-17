package com.qaltera.todolistsample

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

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
): Serializable {

    @PrimaryKey(autoGenerate = true)
    var id = 0
}