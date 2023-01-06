package com.example.mymodule

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("SELECT * FROM task_db")
    fun getAllTasks() : LiveData<List<Task>>

    @Query("SELECT * FROM task_db WHERE checked= :isChecked")
    fun getCheckedTasks(isChecked : Boolean) : LiveData<List<Task>>
}