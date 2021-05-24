package com.sila.roomdb.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sila.roomdb.model.Note

@Database(entities = [Note::class], version = 1/*, exportSchema = false*/) //false->doesn't need to keep the history
abstract class NoteDatabase:RoomDatabase() { //contains database holder
    abstract fun noteDao(): NoteDao

    companion object{
        @Volatile //this field would be visible to other classes
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context):NoteDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}