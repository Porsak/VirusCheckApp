package com.example.viruscheckapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.github.kittinunf.fuel.Fuel
import kotlinx.android.synthetic.main.activity_investigate.*

private val LOG_TAG = SearchActivity::class.java.simpleName
var fileNameChoose = ""
var fileHashChoose = ""
var responseText = ""
private var id = 0

class InvestigateActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_investigate)

        val text: TextView = findViewById<EditText>(R.id.textView13)
        text.text = fileNameChoose

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        button2.setOnClickListener {

            /*Log.d(LOG_TAG, fileHashChoose)
            sendGet(fileHashChoose)
            val resultText: TextView = findViewById(R.id.textView9)
            resultText.text = responseText

            Log.d(LOG_TAG, responseText)*/

            Log.d(LOG_TAG, mainViewModel.getValue(id).toString())

        }

        textView13.setOnClickListener {
            Log.d(LOG_TAG, "Text view clicked")
            id=1
        }
    }

    private fun sendGet(Hash: String) {
        Fuel.get("https://www.virustotal.com/vtapi/v2/file/report?apikey=$APIKey&resource=$Hash")
            .response { request, response, _ -> println(request)
                responseText = response.toString()
                Log.d(LOG_TAG, "Requested")
                Log.d(LOG_TAG, responseText)
            }
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

}