package com.example.viruscheckapp

import androidx.datastore.CorruptionException
import androidx.datastore.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

class MySerializer: Serializer<SearchObject> {

    override fun readFrom(input: InputStream): SearchObject {
        try {
            return SearchObject.parseFrom(input)
        }catch (exception: InvalidProtocolBufferException){
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override fun writeTo(t: SearchObject, output: OutputStream) {
        t.writeTo(output)
    }
}