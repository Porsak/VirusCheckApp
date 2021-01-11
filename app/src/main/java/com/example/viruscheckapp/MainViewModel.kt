package com.example.viruscheckapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repository = ProtoRepository(application)

    val fileName = repository.readProto.asLiveData()

    fun updateValue(fileName: String, hashFile: String) = viewModelScope.launch(Dispatchers.IO){
        repository.updateValue(fileName, hashFile)
    }

}