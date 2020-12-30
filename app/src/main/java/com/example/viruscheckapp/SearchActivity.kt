package com.example.viruscheckapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.FileInputStream
import java.io.InputStream
import java.security.MessageDigest
import kotlin.experimental.and


private val LOG_TAG = SearchActivity::class.java.simpleName
private var url = ""

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
    }

    override fun onStart() {
        super.onStart()
        Log.d(LOG_TAG, "-------")
        Log.d(LOG_TAG, "onStart")
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 111 && resultCode == RESULT_OK) {
            val selectedFile = data?.data //The uri with the location of the file
            val resultText: TextView = findViewById(R.id.textView2)
            resultText.text = selectedFile.toString()
            url = selectedFile.toString()
        }
    }

    fun runHash(view: View) {
        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "Button clicked!")
        Log.d(LOG_TAG, url)
        fileToMD5(url)
    }

    private fun fileToMD5(filePath: String?): String? {
        var inputStream: InputStream? = null
        Log.d(LOG_TAG, "Hash start!")
        return try {
            inputStream = FileInputStream(filePath)
            Log.d(LOG_TAG, inputStream.toString())
            val buffer = ByteArray(1024)
            val digest: MessageDigest = MessageDigest.getInstance("MD5")
            var numRead = 0
            while (numRead != -1) {
                numRead = inputStream.read(buffer)
                if (numRead > 0) digest.update(buffer, 0, numRead)
            }
            val md5Bytes: ByteArray = digest.digest()
            Log.d(LOG_TAG, md5Bytes.toString())
            convertHashToString(md5Bytes)
        } catch (e: Exception) {
            null
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close()
                } catch (e: Exception) {
                }
            }
        }
    }

    private fun convertHashToString(md5Bytes: ByteArray): String? {
        var returnVal = ""
        for (i in md5Bytes.indices) {
            returnVal += ((md5Bytes[i] and 0xff.toByte()) + 0x100).toString(16).substring(1)
        }
        var resultHash: TextView = findViewById(R.id.textView5)
        resultHash.text = returnVal.toString().toUpperCase()
        Log.d(LOG_TAG, returnVal.toString())
        return returnVal.toUpperCase()
    }

    override fun onPause() {
        super.onPause()
        Log.d(LOG_TAG, "-------")
        Log.d(LOG_TAG, "onPause")
    }
}