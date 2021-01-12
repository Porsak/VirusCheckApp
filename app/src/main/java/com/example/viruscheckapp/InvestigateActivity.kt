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

private const val KEY_RESULT = "response"
private const val KEY_APIKEY = "apikey"

var fileNameChoose = ""
var fileHashChoose = ""
private var ResponseText = ""

class InvestigateActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_investigate)

        Log.d(LOG_TAG, "-------")
        Log.d(LOG_TAG, "onCreate")

        var text: TextView = findViewById(R.id.textView13)
        text.text = fileNameChoose
        var text2: TextView = findViewById(R.id.textView10)
        text.text = fileHashChoose

        /*************************************Save persistent data*********************************/
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.fileName.observe(this, {
            text.text = it.fileName
            fileNameChoose = it.fileName
            text2.text = it.hashFile
            fileHashChoose = it.hashFile
        })


        /***************************************Send request**************************************/
        button2.setOnClickListener {
            Log.d(LOG_TAG, fileHashChoose)

            val text3: TextView = findViewById(R.id.editTextTextPersonName4) as EditText
            APIKey = text3.text.toString()

            sendGet(fileHashChoose)
        }

        /****************************************Save user input***************************************/

        if (savedInstanceState != null) {
            ResponseText = savedInstanceState.getString(KEY_RESULT).toString()
            APIKey = savedInstanceState.getString(KEY_APIKEY).toString()
        }

        var resultHash: TextView = findViewById(R.id.textView9)
        resultHash.text = ResponseText

        val text4: TextView = findViewById(R.id.editTextTextPersonName4)
        text4.text = APIKey

    }

    private fun sendGet(Hash: String) {
        Fuel.get("https://www.virustotal.com/vtapi/v2/file/report?apikey=$APIKey&resource=$Hash")
            .response { request, response, _ -> println(request)
                ResponseText = response.toString()
                Log.d(LOG_TAG, "Requested")
                Log.d(LOG_TAG, ResponseText)

                val resultText: TextView = findViewById(R.id.textView9)
                resultText.text = ResponseText
            }
    }

    /*****************************************Save layout state************************************/

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        if (savedInstanceState != null) {
            super.onSaveInstanceState(savedInstanceState)
        }

        val text5: TextView = findViewById(R.id.textView9)
        ResponseText = text5.text.toString()

        val text6 = findViewById<EditText>(R.id.editTextTextPersonName4)
        APIKey = text6.text.toString()

        Log.d(LOG_TAG, "-------")
        Log.d(LOG_TAG, "onSave")

        savedInstanceState.putString(KEY_RESULT, ResponseText)
        savedInstanceState.putString(KEY_APIKEY, APIKey)
    }

    /*********************************************************************************************/

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