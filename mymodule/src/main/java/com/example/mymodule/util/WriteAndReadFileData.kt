package com.example.mymodule.util

import android.os.Environment
import org.json.JSONObject
import java.io.*

class WriteAndReadFileData {

    private val TAG = WriteAndReadFileData::class.java.name
    private val FILEPATH_JSON = "/sdcard/file1.json"
    private val FILEPATH_TXT = Environment.getExternalStorageDirectory().path + "/file2.txt"
    private lateinit var bufferReader : BufferedReader
    private lateinit var bufferedWriter: BufferedWriter

    fun writeTextData(writeFile : String) {
        try {
            val file = File(FILEPATH_TXT)
            if (file.exists())
                file.delete()
            bufferedWriter = BufferedWriter(FileWriter(FILEPATH_TXT))
            bufferedWriter.write(writeFile)
            println("$TAG writeTextData ::  $writeFile")
        } catch (e:Exception) {
            e.printStackTrace()
        } finally {
            try {
                bufferedWriter?.close()
            } catch (e:Exception) {
                e.printStackTrace()
            }
        }

    }

    fun readTextData() {
        try {
            bufferReader = BufferedReader(FileReader(FILEPATH_TXT))
            var line : String?
            val sb = StringBuilder()
            while (bufferReader.readLine().also { line = it } != null) {
                sb.append(line)
            }
            println("$TAG readTextData :: $sb ")
        } catch (e : Exception) {
            e.printStackTrace()
        } finally {
            try {
                bufferReader?.close()
            } catch (e:Exception) {
                e.printStackTrace()
            }

        }
    }

    fun writeJSONData(key : String, value: String) {
        try {
            val file = File(FILEPATH_JSON)

            if (file.exists())
                file.delete()

            bufferedWriter = BufferedWriter(FileWriter(FILEPATH_JSON))

            val jsonObject = JSONObject()
            jsonObject.put(key, value)

            bufferedWriter.write(jsonObject.toString())
            println("$TAG writeJSONData :: key $key : value $value")
        } catch (e:Exception) {
            e.printStackTrace()
        } finally {
            try {
                bufferedWriter?.close()
            } catch (e:Exception) {
                e.printStackTrace()
            }
        }

    }

    fun readJSONData(key: String) {
        try {
            bufferReader = BufferedReader(FileReader(FILEPATH_JSON))
            val sb = StringBuilder()
            var line : String?
            while (bufferReader.readLine().also { line = it} != null) {
                sb.append(line)
            }
            val jsonObject = JSONObject(sb.toString())
            val dataValue = jsonObject.getString(key)  //jsonObject.getJSONObject(key) // for JSON object
            println("$TAG readJSONData :: key $key : value : $dataValue")


            println("$TAG readJSONData :: key $key : value $sb")
        } catch (e:Exception) {
            e.printStackTrace()
        } finally {
            try {
                bufferReader?.close()
            } catch (e:Exception) {
                e.printStackTrace()
            }
        }

    }
}