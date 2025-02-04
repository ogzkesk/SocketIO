package com.example.socket.domain.result

interface ResultOf<out T> {
    data class Success<T>(val data: T) : ResultOf<T>
    data class Failure<T>(val throwable: Throwable, val data: T? = null) : ResultOf<T>

    val isSuccess: Boolean
        get() = this is Success

    val isFailure: Boolean
        get() = this is Error

    val takeIfSuccess: T?
        get() = (this as? Success)?.data
}