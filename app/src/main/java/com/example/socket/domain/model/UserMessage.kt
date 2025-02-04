package com.example.socket.domain.model


data class UserMessage(
    val type: MessageType,
    val message: String,
    val user: User
)
