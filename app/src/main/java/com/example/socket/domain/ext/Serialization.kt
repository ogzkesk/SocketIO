package com.example.socket.domain.ext

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import org.json.JSONArray

@Throws(SerializationException::class)
@OptIn(InternalSerializationApi::class)
inline fun <reified T : Any> T.toJson(): String {
    return Json.encodeToString(T::class.serializer(), this)
}

@Throws(SerializationException::class)
inline fun <reified R : Any> String.fromJson(): R {
    return Json.decodeFromString(this)
}
