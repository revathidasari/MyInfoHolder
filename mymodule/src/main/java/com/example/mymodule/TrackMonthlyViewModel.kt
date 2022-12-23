package com.example.mymodule

import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//https://medium.com/android-beginners/material-chips-material-components-for-android-dd0ef942e5ce

class TrackMonthlyViewModel(
    private val repository: TrackMonthlyRepository
) : ViewModel() {

   // val monthlyLiveData : LiveData<TrackMonthlyItems> = MutableLiveData()
    fun insertAndUpdate(item: TrackMonthlyItems) = CoroutineScope(Dispatchers.Main).launch {
        repository.insertAndUpdate(item)
    }

    fun delete(item: TrackMonthlyItems) = viewModelScope.launch {
        repository.delete(item)
    }

    fun getAllTrackedMonthlyItems() = repository.getAllTrackedMonthlyItems()

    fun getSelectedMonthDetails(month: String) =
        viewModelScope.launch {
            repository.getSelectedMonthDetails(month)
        }

   // fun addItem(item:TrackMonthlyItems) = runBlocking { repository.insertAndUpdate(item) }

    /*var track = mutableListOf<TrackMonthlyItems>()
    val trackerAdapter = TrackerAdapter(track, this)*/

}

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val trackMonthlyRepository: TrackMonthlyRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TrackMonthlyViewModel::class.java)) {
            return TrackMonthlyViewModel(repository = trackMonthlyRepository) as T
        }
        else throw java.lang.IllegalArgumentException("Unknown view model class")
    }
}