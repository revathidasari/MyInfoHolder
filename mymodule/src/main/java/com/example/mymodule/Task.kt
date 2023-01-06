package com.example.mymodule

import android.widget.ImageButton
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_db")
data class Task(
    @ColumnInfo(name = "title")
    var title : String,

    @ColumnInfo(name = "checked")
    var isChecked : Boolean

) {
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null
}
