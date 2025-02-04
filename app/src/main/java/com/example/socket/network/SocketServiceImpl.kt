package com.example.socket.network

import com.example.socket.domain.result.ResultOf
import com.example.socket.domain.service.SocketService
import io.socket.client.Socket
import javax.inject.Inject

class SocketServiceImpl @Inject constructor(
    private val socket: Socket
) : SocketService {

    override fun connect(): ResultOf<Boolean> {
        return try {
            socket.connect()
            ResultOf.Success(true)
        }catch (e: Exception){
            ResultOf.Failure(e)
        }
    }

    override fun disconnect(): ResultOf<Boolean> {
        return ResultOf.Success(true)
    }

    override fun open(): ResultOf<Boolean> {
        return ResultOf.Success(true)
    }
}