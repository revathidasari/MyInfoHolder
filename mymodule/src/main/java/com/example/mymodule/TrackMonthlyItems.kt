package com.example.mymodule

import android.text.Editable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.temporal.TemporalAmount

@Entity(tableName = "monthly_items")
data class TrackMonthlyItems(

    @PrimaryKey
    @ColumnInfo(name = "month_name")
    val name : String,

    @ColumnInfo(name = "income")
    val incomeAmount : Int,

    @ColumnInfo(name = "savings")
    val savingsAmount : Int,

    @ColumnInfo(name = "expenses")
    val expensesAmount : Int,

    @ColumnInfo(name = "emi")
    val emiAmount : Int,

    @ColumnInfo(name = "investment")
    val investmentAmount : Int,

    @ColumnInfo(name = "spendings")
    val spendingsAmount : Int,

    @ColumnInfo(name = "onfood")
    val onFoodAmount : Int,

    @ColumnInfo(name = "total")
    val totalAmount : Int


)
