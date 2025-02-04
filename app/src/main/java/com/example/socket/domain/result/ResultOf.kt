package com.example.socket.domain.result

interface ResultOf<out T> {
    data class Success<T>(val data: T) : ResultOf<T>
    data class Failure(val throwable: Throwable) : ResultOf<Nothing>

    fun isSuccess(): Boolean = this is Success

    fun isFailure(): Boolean = this is Error

    fun takeIfSuccess(): T? = (this as? Success)?.data
}
