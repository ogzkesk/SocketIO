package com.example.socket.ui.chat

import com.example.socket.domain.model.User
import com.example.socket.domain.model.UserMessage
import com.example.socket.domain.result.ConnectionStatus

data class ChatState(
    val chatText: String = "",
    val messages: List<UserMessage>? = null,
    val currentUser: User? = null,
    val connectionStatus: ConnectionStatus? = null
)
