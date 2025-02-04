package com.example.socket.network

import com.example.socket.domain.result.ConnectionStatus
import com.example.socket.domain.result.ResultOf
import com.example.socket.domain.service.SocketService
import io.socket.client.Socket
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SocketServiceImpl @Inject constructor(
    private val socket: Socket,
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) : SocketService {

    override fun connect(
        result: (ConnectionStatus) -> Unit
    ) {
        socket.on(Socket.EVENT_CONNECT) {
            result.invoke(ConnectionStatus.CONNECTED)
            println("Connected to server ${it.toList()}")
        }
        socket.on(Socket.EVENT_CONNECT_ERROR) { args ->
            result.invoke(ConnectionStatus.FAILURE)
            println("Connection Error: ${args[0]}")
        }
        socket.connect()
    }

    override fun <T> send(event: String, data: T) {
        socket.emit(event, data) // TODO response with ack
    }

    override fun <T> get(event: String): Flow<ResultOf<T>> {
        return flow {
            socket.on(event) { args ->
                val value = args[0] as T
                coroutineScope.launch {
                    emit(ResultOf.Success(value))
                }
            }
        }
    }
}