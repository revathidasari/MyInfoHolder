package com.example.mymodule.util

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.util.Log

class SharedPref(context: Context) {
    private val MYLOCALPREF = "MyLocalPrefs"

    var sharedPref : SharedPreferences
    var editor : SharedPreferences.Editor

    init {
        sharedPref = context.getSharedPreferences(MYLOCALPREF, Context.MODE_PRIVATE)
        editor = sharedPref.edit()
    }

    fun addToPreference(name : String, amount : Int) {
        editor.putInt(name, amount)
        editor.commit()
    }

    fun getFromPreference(name: String) : Int {
        return sharedPref.getInt(name, 0)
    }

    fun removeFromPreference(name: String) {
        editor.remove(name)
    }

    fun gainOrLoss() : Int {
        val invest = sharedPref.getInt("investment",0)
        val profit = sharedPref.getInt("profit",0)
        val loss = sharedPref.getInt("loss",0)
        return profit-loss
    }

    fun getAllData() {
        val keys: Map<String, *> = sharedPref.getAll()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //(or)  for ((key,value) in keys) { }
            keys.forEach { (key, value) ->
                Log.d("revathi","key $key value $value")
            }
        }
    }
}