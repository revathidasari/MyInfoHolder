package com.example.mymodule

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase : RoomDatabase() {

    abstract val noteDao : NoteDao

    companion object {

        @Volatile
        private var noteDBInstance : NoteDatabase? = null

        fun createAndGetNoteDBInstance(context: Context) : NoteDatabase  {
            synchronized(this) {
                return noteDBInstance ?: Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_db")
                    .build().also {
                        noteDBInstance = it
                    }
            }
        }

    }
}