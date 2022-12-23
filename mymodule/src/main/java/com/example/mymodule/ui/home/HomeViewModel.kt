package com.example.mymodule.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymodule.Task
import com.example.mymodule.TaskAdapterView

class HomeViewModel : ViewModel() {
//(application : Application) : AndroidViewModel(application) {


    private val _text = MutableLiveData<String>().apply {
        value = "This is Task Fragment"
      //  value = application.applicationContext.getString(R.string.check)//"This is home Fragment"

    }
    val text: LiveData<String> = _text


    //Tasks List
    var taskList = mutableListOf(
        Task("Add daily tasks here", false)
    )

    val taskAdapterView = TaskAdapterView(taskList, this)

    fun delete(currentItem: Task) {
        Log.d("revathi","vm " +currentItem)
       if (taskList.contains(currentItem)) {
           taskList.remove(currentItem)
       }
    }

    fun isTaskChecked() : Int {
        return 0
    }


}