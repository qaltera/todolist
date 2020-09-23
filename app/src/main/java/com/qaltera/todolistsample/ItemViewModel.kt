package com.qaltera.todolistsample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
 * ************************************************
 * NoteViewModel
 * Date: 2020-09-16
 * Author: Yulia Rogovaya
 * ************************************************
 */

class ItemViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ItemRepository(application.applicationContext,
        viewModelScope)
    // Using LiveData and caching what allNotes returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allItems = repository.allItems

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(item: Item) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(item)
    }

    fun delete(item: Item) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(item)
    }

    fun update(item: Item) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(item)
    }
}