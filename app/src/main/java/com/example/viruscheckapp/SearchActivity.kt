package com.example.viruscheckapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.github.kittinunf.fuel.Fuel
import kotlinx.android.synthetic.main.activity_search.*

private val LOG_TAG = SearchActivity::class.java.simpleName

private const val KEY_RESULT = "response"
private const val KEY_HASH = "hash"
private const val KEY_APIKEY = "apikey"
private const val KEY_FILENAME = "filename"

private var fileNameSearch = ""
private var HashSum = ""
var APIKey = ""
private var ResponseText = ""

class SearchActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        Log.d(LOG_TAG, "-------")
        Log.d(LOG_TAG, "onCreate")

     /*************************************Save persistent data************************************/

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        button4.setOnClickListener {
            val fileName = editTextTextPersonName3.text.toString()
            val fileHash = editTextTextPersonName.text.toString()
            mainViewModel.updateValue(fileName, fileHash)

            fileNameChoose = fileName
            fileHashChoose = fileHash
        }

    /****************************************Save user input***************************************/

        if (savedInstanceState != null) {
            ResponseText = savedInstanceState.getString(KEY_RESULT).toString()
            HashSum = savedInstanceState.getString(KEY_HASH).toString()
            APIKey = savedInstanceState.getString(KEY_APIKEY).toString()
            fileNameSearch = savedInstanceState.getString(KEY_FILENAME).toString()
        }

        var resultHash: TextView = findViewById(R.id.textView5)
        resultHash.text = ResponseText

        val text: TextView = findViewById<EditText>(R.id.editTextTextPersonName)
        text.text = HashSum

        val text2: TextView = findViewById<EditText>(R.id.editTextTextPersonName2)
        text2.text = APIKey

        val text3: TextView = findViewById<EditText>(R.id.editTextTextPersonName3)
        text3.text = fileNameSearch

    }
    /****************************************Run API request***************************************/

    fun runAPISearch(view: View) {
        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "Button clicked!")

        val text: TextView = findViewById<View>(R.id.editTextTextPersonName) as EditText
        HashSum = text.text.toString()

        val text2: TextView = findViewById<View>(R.id.editTextTextPersonName2) as EditText
        APIKey = text2.text.toString()

        val text3: TextView = findViewById<View>(R.id.editTextTextPersonName3) as EditText
        fileNameSearch = text3.text.toString()

        sendGet(HashSum)
    }

    /*****************************************Run API request**************************************/

    private fun sendGet(Hash: String): String {
        Fuel.get("https://www.virustotal.com/vtapi/v2/file/report?apikey=$APIKey&resource=$Hash")
            .response { request, response, result -> println(request)
                println(response)

                Log.d(LOG_TAG, "1-------")
                //Log.d(LOG_TAG, response.toString())

                val (bytes, error) = result
                if (bytes != null) {
                    Log.d(LOG_TAG, "2-------")
                    println("[response bytes] ${String(bytes)}")
                }

                Log.d(LOG_TAG, "-------")
                Log.d(LOG_TAG, "Response control")
                ResponseText = response.toString()

                var resultText: TextView = findViewById(R.id.textView5)
                resultText.text = ResponseText
            }
        return ResponseText
    }

    /*****************************************Save layout state************************************/

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        if (savedInstanceState != null) {
            super.onSaveInstanceState(savedInstanceState)
        }
        Log.d(LOG_TAG, "-------")
        Log.d(LOG_TAG, "onSave")

        val resultHash: TextView = findViewById(R.id.textView5)
        ResponseText = resultHash.text.toString()

        val text = findViewById<View>(R.id.editTextTextPersonName) as EditText
        HashSum = text.text.toString()

        val text2 = findViewById<View>(R.id.editTextTextPersonName2) as EditText
        APIKey = text2.text.toString()

        val text3: TextView = findViewById<EditText>(R.id.editTextTextPersonName3) as EditText
        fileNameSearch = text3.text.toString()

        savedInstanceState.putString(KEY_RESULT, ResponseText)
        savedInstanceState.putString(KEY_HASH, HashSum)
        savedInstanceState.putString(KEY_APIKEY, APIKey)
        savedInstanceState.putString(KEY_FILENAME, fileNameSearch)
    }

    /**********************************************************************************************/

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