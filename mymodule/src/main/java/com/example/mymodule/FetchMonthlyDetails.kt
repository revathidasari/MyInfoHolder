package com.example.mymodule

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.LiveData
import com.example.mymodule.util.SharedPref


class FetchMonthlyDetails : AppCompatActivity() {

    private var backBtn : ImageButton? = null
    var currentMonth : TextView? = null
    var incomeAmount : EditText? = null
    var savingsAmount : EditText? = null
    var expensesAmount : EditText? = null
    var emiAmount : EditText? = null
    var investmentAmount : EditText? = null
    var spendingsAmount : EditText? = null
    var onFoodAmount : EditText? = null
    var totalText : TextView? = null
    var totalButton : Button? = null

    lateinit var monthTotal : LiveData<Int>

    val trackMonthlyViewModel : TrackMonthlyViewModel by viewModels { ViewModelFactory(initDb()) }

    private fun initDb() : TrackMonthlyRepository {
        val database = TrackMonthlyDatabase.getInstance(this)
        return TrackMonthlyRepository(database)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_month_specific)

        backBtn = findViewById(R.id.backArrow)
        currentMonth= findViewById(R.id.currentMonth)
        incomeAmount = findViewById(R.id.incomeAmount)
        savingsAmount = findViewById(R.id.savingsAmount)
        expensesAmount = findViewById(R.id.expensesAmount)
        emiAmount = findViewById(R.id.emiAmount)
        investmentAmount = findViewById(R.id.investmentAmount)
        spendingsAmount = findViewById(R.id.spendingsAmount)
        onFoodAmount = findViewById(R.id.onFoodAmount)
        totalText = findViewById(R.id.totalText)
        totalButton = findViewById(R.id.totalButton)
        currentMonth?.text = intent.getStringExtra("month").toString()

        backBtn?.setOnClickListener{
            onBackPressed()
        }


        getAllMonthTotal()
       // monthTotal.observe(this, )
        val sharedPref = SharedPref(this)
        sharedPref.addToPreference("investment",editToIntConverter(investmentAmount))
        sharedPref.addToPreference("profit",10)
        sharedPref.addToPreference("loss",1)
        Log.d("revathi","${sharedPref.getFromPreference("investment")}  eresult ${sharedPref.gainOrLoss()}   " +
                "${sharedPref.getAllData()} ::")

        totalButton?.setOnClickListener{
            addMonthlyData()
            totalText?.text = sumAmount().toString()
        }

      //  val factory = TrackMonthlyViewModel(repository)
      //  val viewModelT = ViewModelProvider(this).get(TrackMonthlyViewModel::class.java)

    }

    private fun editToIntConverter(editText: EditText?) : Int {
        if (editText?.text.isNullOrEmpty())
            return 0
        return (editText?.text.toString().toInt())
    }

    private fun sumAmount() : Int {
        val sum = editToIntConverter(incomeAmount)-editToIntConverter(savingsAmount)-editToIntConverter(expensesAmount)-
                editToIntConverter(emiAmount)-editToIntConverter(investmentAmount)-editToIntConverter(spendingsAmount)-
                editToIntConverter(onFoodAmount)
        return sum
    }


    fun specificMonthData(trackMonthlyItems: TrackMonthlyItems) {
        incomeAmount?.setText(trackMonthlyItems.incomeAmount.toString())
        savingsAmount?.setText(trackMonthlyItems.savingsAmount.toString())
        expensesAmount?.setText(trackMonthlyItems.expensesAmount.toString())
        emiAmount?.setText(trackMonthlyItems.emiAmount.toString())
        investmentAmount?.setText(trackMonthlyItems.investmentAmount.toString())
        spendingsAmount?.setText(trackMonthlyItems.spendingsAmount.toString())
        onFoodAmount?.setText(trackMonthlyItems.onFoodAmount.toString())
        totalText?.setText(trackMonthlyItems.totalAmount.toString())
    }

    fun getAllMonthTotal() {
        trackMonthlyViewModel.getAllTrackedMonthlyItems().observe(this) { list ->
            Log.d("revathi", "data $list")
            list.map {
                Log.d("revathi", "${it.name} data 1 $it ")
                if (currentMonth?.text == it.name) {
                    Log.d("revathi", "${it.incomeAmount} ")
                    specificMonthData(it)
//                    TrackerAdapter(list)
                }
            }
        }
    }

    fun getMonthSpecificData(month : String) : Int {
        trackMonthlyViewModel.getAllTrackedMonthlyItems().observe(this){
         //   monthTotal = sumAmount()
        }
        trackMonthlyViewModel.getSelectedMonthDetails(month)
        return 0
    }

    fun addMonthlyData() {
        val trackMonthlyItems = TrackMonthlyItems(
            currentMonth.toString(),
            editToIntConverter(incomeAmount),
            editToIntConverter(savingsAmount),
            editToIntConverter(expensesAmount),
            editToIntConverter(emiAmount),
            editToIntConverter(investmentAmount),
            editToIntConverter(spendingsAmount),
            editToIntConverter(onFoodAmount),
            sumAmount()
        )
        trackMonthlyViewModel.insertAndUpdate(trackMonthlyItems)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
