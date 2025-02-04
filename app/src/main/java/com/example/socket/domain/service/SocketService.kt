package com.example.socket.domain.service

import com.example.socket.domain.result.ConnectionStatus
import com.example.socket.domain.result.ResultOf
import kotlinx.coroutines.flow.Flow

interface SocketService {
    fun connect(result: (ConnectionStatus) -> Unit)
    fun <T> send(event: String, data: T)
    fun <T> get(event: String): Flow<ResultOf<T>>
}