package com.example.viruscheckapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

private val LOG_TAG = MainActivity::class.java.simpleName

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
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

    fun launchSearchActivity(view: View) {
        val intent = Intent(this, SearchActivity::class.java)
        startActivity(intent);
        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "Button clicked!")
    }

    fun launchInvestigateActivity(view: View) {
        val intent = Intent(this, InvestigateActivity::class.java)
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