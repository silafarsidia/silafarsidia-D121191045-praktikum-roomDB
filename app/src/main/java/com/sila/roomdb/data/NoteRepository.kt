package com.sila.roomdb.data

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao) { //class abstracts access to multiple data sources

    val readAllData:LiveData<List<Note>> = noteDao.readAllData()

    suspend fun addNote(note:Note){
        noteDao.addNote(note)
    }
}