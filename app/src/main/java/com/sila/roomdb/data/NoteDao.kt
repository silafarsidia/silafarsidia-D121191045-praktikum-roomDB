package com.sila.roomdb.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.sila.roomdb.model.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE) //OnConflict -> ignore when other addNote exists
    suspend fun addNote(note: Note) //suspend -> needed for coroutines later

    @Update
    suspend fun updateNote(note: Note)

    @Query("SELECT * FROM note_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(note: Note)
}