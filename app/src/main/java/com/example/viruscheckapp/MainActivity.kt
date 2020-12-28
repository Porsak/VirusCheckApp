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

    }

    fun launchSecondActivity(view: View) {
        Log.d(LOG_TAG, "Button clicked!");
        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent);
    }
}