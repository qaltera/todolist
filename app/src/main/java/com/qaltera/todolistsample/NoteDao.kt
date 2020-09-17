package com.qaltera.todolistsample

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/*
 * ************************************************
 * NoteDao
 * Date: 2020-09-16
 * Author: Yulia Rogovaya
 * ************************************************
 */

@Dao
interface NoteDao {

    @Insert
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("DELETE FROM note_table")
    fun deleteAllNotes()

    @Query("SELECT * FROM note_table ORDER BY importance DESC")
    fun allNotes(): LiveData<List<Note>>
}