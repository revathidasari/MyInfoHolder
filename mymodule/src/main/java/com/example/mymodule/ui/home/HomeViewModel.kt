package com.example.mymodule.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymodule.Task
import com.example.mymodule.TaskAdapterView
import com.example.mymodule.TaskDao
import kotlinx.coroutines.launch

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

//    val taskAdapterView = TaskAdapterView(taskList, this)

    fun delete(currentItem: Task) {
        Log.d("revathi","vm " +currentItem)
       if (taskList.contains(currentItem)) {
           taskList.remove(currentItem)
       }
    }

    fun isTaskChecked() : Int {
        return 0
    }

    fun insertTask(task: Task, taskDao: TaskDao) {
        viewModelScope.launch {
            try {
                taskDao.insertTask(task)
            } catch (e:Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteTask(task: Task, taskDao: TaskDao) {
        viewModelScope.launch {
            taskDao.deleteTask(task)
        }
    }

    fun getAllTasks(taskDao: TaskDao) : LiveData<List<Task>> {
        return taskDao.getAllTasks()
    }

}