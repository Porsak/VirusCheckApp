package com.example.viruscheckapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.kittinunf.fuel.Fuel
import java.io.File
import java.io.FileInputStream
import java.io.IOException

private val LOG_TAG = SearchActivity::class.java.simpleName
private var HashSum = ""
private var APIKey = ""

private var ResponseText = ""

private const val KEY_RESULT = "response"
private const val KEY_HASH = "hash"
private const val KEY_APIKEY = "apikey"

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        Log.d(LOG_TAG, "-------")
        Log.d(LOG_TAG, "onCreate")
        if (savedInstanceState != null) {
            ResponseText = savedInstanceState.getString(KEY_RESULT).toString()
            HashSum = savedInstanceState.getString(KEY_HASH).toString()
            APIKey = savedInstanceState.getString(KEY_APIKEY).toString()
        }

        var resultHash: TextView = findViewById(R.id.textView5)
        resultHash.text = ResponseText

        val text: TextView = findViewById<EditText>(R.id.editTextTextPersonName)
        text.text = HashSum
        val text2: TextView = findViewById<EditText>(R.id.editTextTextPersonName2)
        text2.text = APIKey

    }

    fun runAPISearch(view: View) {
        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "Button clicked!")
        Log.d(LOG_TAG, HashSum)

        val text = findViewById<View>(R.id.editTextTextPersonName) as EditText
        HashSum = text.text.toString()
        val text2 = findViewById<View>(R.id.editTextTextPersonName2) as EditText
        APIKey = text2.text.toString()

        sendGet()
    }

    private fun sendGet(){
        Fuel.get("https://www.virustotal.com/vtapi/v2/file/report?apikey=$APIKey&resource=$HashSum")
            .response {
                    request, response, result -> println(request)
                println(response)
                Log.d(LOG_TAG, response.toString())
                val (bytes, error) = result
                if (bytes != null) {
                    println("[response bytes] ${String(bytes)}")

                    ResponseText = response.toString()
                    Log.d(LOG_TAG, "-------")
                    Log.d(LOG_TAG, "Response control")
                    var resultHash: TextView = findViewById(R.id.textView5)
                    resultHash.text = ResponseText
                }
            }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        if (savedInstanceState != null) {
            super.onSaveInstanceState(savedInstanceState)
        }
        Log.d(LOG_TAG, "-------")
        Log.d(LOG_TAG, "onSave")
        savedInstanceState.putString(KEY_RESULT, ResponseText)
        savedInstanceState.putString(KEY_HASH, HashSum)
        savedInstanceState.putString(KEY_APIKEY, APIKey)
    }

    override fun onPause() {
        super.onPause()
        Log.d(LOG_TAG, "-------")
        Log.d(LOG_TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(LOG_TAG, "-------")
        Log.d(LOG_TAG, "onStop")
    }

    override fun onResume() {
        super.onResume()
        Log.d(LOG_TAG, "-------")
        Log.d(LOG_TAG, "onResume")
    }

    fun searchFile(view: View) {
        val intent = Intent()
            .setType("*/*")
    .setAction(Intent.ACTION_GET_CONTENT)

    startActivityForResult(Intent.createChooser(intent, "Select a file"), 111)
    }
 /*
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 111 && resultCode == RESULT_OK) {
            val selectedFile = data?.data //The uri with the location of the file
            val resultText: TextView = findViewById(R.id.textView2)
            resultText.text = selectedFile.toString()
            DataPath = selectedFile.toString()
        }
    }
*/

/*
    private fun sendPost() {
        Fuel.post("https://www.virustotal.com/vtapi/v2/file/scan", listOf("apikey" to "$APIKey", "file" to "e3971e10ff61c633591999b2054e7593bfd2a7656b663a015df276a7691eab91"))

                .response { request, response, result -> println(request)

                    println(response)
                    Log.d(LOG_TAG, response.toString())
                    val (bytes, error) = result
                    if (bytes != null) {
                        println("[response bytes] ${String(bytes)}")
                        var resultHash: TextView = findViewById(R.id.textView5)
                        resultHash.text = response.toString()
                    }
                }
    }
*/

}