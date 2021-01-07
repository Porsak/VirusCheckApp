package com.example.viruscheckapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.io.InputStream
import java.io.OutputStream

private val LOG_TAG = SearchActivity::class.java.simpleName

class InvestigateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_investigate)
    }
    /*
    object UserPreferencesSerializer : Serializer<giveData> {
        override val defaultValue: UserPreferences = giveData.getDefaultInstance()
        override fun readFrom(input: InputStream): giveData {
            try {
                return giveData.parseFrom(input)
            } catch (exception: InvalidProtocolBufferException) {
                throw CorruptionException("Cannot read proto.", exception)
            }
        }

        override fun writeTo(t: UserPreferences, output: OutputStream) = t.writeTo(output)
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