package com.example.mymodule

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [TrackMonthlyItems::class],
    version = 1
)
abstract class TrackMonthlyDatabase : RoomDatabase() {

    abstract fun getTrackMonthlyDao() : TrackMonthlyDao

    companion object {

        @Volatile
        private var instance : TrackMonthlyDatabase? = null

        fun getInstance(context: Context) : TrackMonthlyDatabase {
            synchronized(this) {
                return instance ?: createDatabase(context).also {
                    instance = it
                }
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
            TrackMonthlyDatabase::class.java,
            "monthly_track_db")
                .allowMainThreadQueries()
                .build()


    }
}