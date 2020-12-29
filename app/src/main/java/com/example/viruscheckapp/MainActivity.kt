package com.example.viruscheckapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity


private val LOG_TAG = MainActivity::class.java.simpleName

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "onCreate");
    }

    override fun onStart() {
        super.onStart()
        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "onResume")
    }

    fun launchSecondActivity(view: View) {
        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent);
        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "Button clicked!")
    }

    override fun onPause() {
        super.onPause()
        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "onPause")
    }
}