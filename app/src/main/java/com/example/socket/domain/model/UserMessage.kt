package com.example.socket.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserMessage(
    val type: MessageType,
    val message: String,
    val user: User,
    val timestamp: Long,
    val local: Boolean = false
)
