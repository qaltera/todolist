package com.qaltera.todolistsample

import android.app.Application
import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope

/*
 * ************************************************
 * NoteRepository
 * Date: 2020-09-16
 * Author: Yulia Rogovaya
 * ************************************************
 */

class NoteRepository(
    context: Context,
    scope: CoroutineScope
) {
    private val noteDao: NoteDao
    val allNotes: LiveData<List<Note>>

    init {
        val database: NoteDatabase = NoteDatabase.getDatabase(
            context,
            scope
        )
        noteDao = database.noteDao()
        allNotes = noteDao.allNotes()
    }

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    suspend fun update(note: Note) {
        noteDao.update(note)
    }

    suspend fun delete(note: Note) {
        noteDao.delete(note)
    }

    suspend fun deleteAllNotes() {
        noteDao.deleteAllNotes()
    }
}