package com.example.mymodule

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Task::class],
    version = 1
)
abstract class TaskDatabase : RoomDatabase() {

    abstract val taskDao : TaskDao

    companion object {

        @Volatile
        private var taskInstance : TaskDatabase? = null

        fun createAndGetTaskDBInstance(context: Context) : TaskDatabase {
            synchronized(this) {
                return taskInstance ?: Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "task_db"
                ).build().also {
                    taskInstance = it
                }

            }

        }

    }
}