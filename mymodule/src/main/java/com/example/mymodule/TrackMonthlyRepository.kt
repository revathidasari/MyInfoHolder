package com.example.mymodule

class TrackMonthlyRepository(
    private val db : TrackMonthlyDatabase
) {

    suspend fun insertAndUpdate(item: TrackMonthlyItems) = db.getTrackMonthlyDao().insertAndUpdate(item)

    suspend fun delete(item: TrackMonthlyItems) = db.getTrackMonthlyDao().delete(item)

    fun getAllTrackedMonthlyItems() = db.getTrackMonthlyDao().getAllTrackedMonthlyItems()

    suspend fun getSelectedMonthDetails(month: String): TrackMonthlyItems
    = db.getTrackMonthlyDao().getSelectedMonthDetails(month)
}