package com.example.mymodule.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymodule.Note
import com.example.mymodule.NoteAdapterView

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    //Note List
    var noteList = mutableListOf(
        Note("Personal", "", false),
    )

    val noteAdapterView = NoteAdapterView(noteList)


}