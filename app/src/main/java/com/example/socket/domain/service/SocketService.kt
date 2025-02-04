package com.example.socket.domain.service

import com.example.socket.domain.result.ResultOf

interface SocketService {
    fun connect(): ResultOf<Boolean>
    fun disconnect(): ResultOf<Boolean>
    fun open(): ResultOf<Boolean>
}