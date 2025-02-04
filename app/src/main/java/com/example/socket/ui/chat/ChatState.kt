package com.example.socket.ui.chat

import com.example.socket.domain.model.UserMessage

data class ChatState(
    val chatText: String = "",
    val messages: List<UserMessage>? = null
)
