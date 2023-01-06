package com.example.mymodule.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymodule.Note
import com.example.mymodule.NoteAdapterView
import com.example.mymodule.NoteDao
import com.example.mymodule.NoteDatabase
import kotlinx.coroutines.ExecutorCoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {


    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    //Note List
    var noteList = mutableListOf(
        Note("Personal", "", false),
    )

    var noteAdapterView = NoteAdapterView(emptyList())


    fun addAndSaveNote(note: Note, noteDao: NoteDao) {
/*        noteList.add(note)
        noteAdapterView.notifyItemInserted( //recycler view will update whole items even the items didnt changed
            noteList.size-1
        )*/
        viewModelScope.launch { try {
            noteDao.insertNote(note)
          } catch (e:Exception) { e.printStackTrace() }
        }

    }

    fun getAllNotes(noteDao: NoteDao) : Flow<List<Note>> {
        return noteDao.getAllNotes()
    }
}

/*class BusScheduleViewModelFactory(
    private val scheduleDao: ScheduleDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BusScheduleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BusScheduleViewModel(scheduleDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}*/