package com.sila.roomdb.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.sila.roomdb.data.NoteDatabase
import com.sila.roomdb.repository.NoteRepository
import com.sila.roomdb.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Note>>
    private val repository: NoteRepository

    init{
        val noteDao = NoteDatabase.getDatabase(application).noteDao()
        repository = NoteRepository(noteDao)
        readAllData = repository.readAllData
    }

    fun addNote(note: Note){
        viewModelScope.launch(Dispatchers.IO){
            repository.addNote(note)
        }
    }

    fun updateNote(note:Note){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateNote(note)
        }
    }
}