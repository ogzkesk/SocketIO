package com.example.socket.domain.service

import com.example.socket.domain.model.User
import com.example.socket.domain.model.UserMessage
import com.example.socket.domain.result.ConnectionStatus

interface SocketService {
    fun connect()
    fun disconnect()

    fun join(user: User)
    fun sendMessage(message: UserMessage)

    fun streamConnectionStatus(callback: (ConnectionStatus) -> Unit)
    fun streamUsers(callback: (List<User>) -> Unit)
    fun streamMessages(callback: (List<UserMessage>) -> Unit)
}