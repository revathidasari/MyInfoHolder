package com.example.mymodule

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_db")
data class Note(

    @ColumnInfo(name = "label")
    val title : String,

    @ColumnInfo(name = "text")
    val description : String,

    @ColumnInfo(name = "selected")
    val isSelected : Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var id : Int?  = null
}
