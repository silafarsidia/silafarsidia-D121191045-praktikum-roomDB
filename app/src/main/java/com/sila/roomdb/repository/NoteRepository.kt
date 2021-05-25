package com.sila.roomdb.repository

import androidx.lifecycle.LiveData
import com.sila.roomdb.data.NoteDao
import com.sila.roomdb.model.Note

class NoteRepository(private val noteDao: NoteDao) { //class abstracts access to multiple data sources

    val readAllData:LiveData<List<Note>> = noteDao.readAllData()

    suspend fun addNote(note: Note){
        noteDao.addNote(note)
    }

    suspend fun updateNote(note: Note){
        noteDao.updateNote(note)
    }

    suspend fun deleteNote(note: Note){
        noteDao.deleteNote(note)
    }

    suspend fun deleteAllNotes(){
        noteDao.deleteAllNotes()
    }
}