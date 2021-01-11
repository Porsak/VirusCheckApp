package com.example.viruscheckapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.github.kittinunf.fuel.Fuel
import kotlinx.android.synthetic.main.activity_investigate.*

private val LOG_TAG = SearchActivity::class.java.simpleName
var fileNameChoose = ""
var fileHashChoose = ""
var responseText = ""

class InvestigateActivity : AppCompatActivity() {

    //private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_investigate)

        /*val text: TextView = findViewById<EditText>(R.id.textView13)
        text.text = fileNameChoose*/

        /*mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.fileName.observe(this, {
            textView9.text = resultMessage
        })*/

        button2.setOnClickListener {

            Log.d(LOG_TAG, fileHashChoose)
            sendGet(fileHashChoose)
            val resultText: TextView = findViewById(R.id.textView9)
            resultText.text = responseText

            Log.d(LOG_TAG, responseText)
            /*val fileName = fileNameChoose
            mainViewModel.updateValue(fileName, fileHashChoose)*/

        }

        textView13.setOnClickListener {
            fileHashChoose
            Log.d(LOG_TAG, "Text view clicked")
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

    /*private fun chooseName(view: View){
        fileHashChoose = textView.toString()
    }*/

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