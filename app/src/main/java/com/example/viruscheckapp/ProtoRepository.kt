package com.example.viruscheckapp

import android.content.Context
import android.util.Log
import androidx.datastore.DataStore
import androidx.datastore.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException

class ProtoRepository(context: Context) {

    private val dataStore: DataStore<SearchObject> = context.createDataStore(
        "my_data",
        serializer = MySerializer()
    )

    val readProto: Flow<SearchObject> = dataStore.data
        .catch { exception->
            if(exception is IOException){
                Log.d("Error", exception.message.toString())
                emit(SearchObject.getDefaultInstance())
            }else{
                throw exception
            }
        }

    suspend fun updateValue(fileName: String, hashFile: String){
        dataStore.updateData { preference->
            preference.toBuilder().setFileName(fileName).setHashFile(hashFile).build()
        }
    }
}