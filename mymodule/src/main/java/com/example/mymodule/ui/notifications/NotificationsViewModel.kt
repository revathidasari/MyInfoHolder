package com.example.mymodule.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymodule.*
import kotlinx.coroutines.launch

class NotificationsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text

    val monthList = listOf(
        Track("January", 0, false),
        Track("February", 0, false),
        Track("March", 0, false),
        Track("April", 0, false),
        Track("May", 0, false),
        Track("June", 0, false),
        Track("July", 0, false),
        Track("August", 0, false),
        Track("September", 0, false),
        Track("October", 0, false),
        Track("November", 0, false),
        Track("December", 0, false)
    )

    val trackAdapterView = TrackAdapterView(tracks = monthList, this)

    fun detailedMonthView(monthTitle: String) {
        this.viewModelScope.launch {
            FetchMonthlyDetails().getMonthSpecificData(monthTitle)
          //NotificationsFragment().specificMonthView(monthTitle)
        }
    }


}