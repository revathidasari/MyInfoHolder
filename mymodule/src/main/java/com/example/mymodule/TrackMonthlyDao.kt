package com.example.mymodule

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface TrackMonthlyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAndUpdate(item : TrackMonthlyItems)

    @Delete
    fun delete(item: TrackMonthlyItems)

    @Query("SELECT * FROM monthly_items")
    fun getAllTrackedMonthlyItems() : LiveData<List<TrackMonthlyItems>> //LiveData<List<TrackMonthlyItems>>

    @Transaction
    @Query("SELECT * FROM monthly_items WHERE month_name = :monthName")
    fun getSelectedMonthDetails(monthName : String) : TrackMonthlyItems
}