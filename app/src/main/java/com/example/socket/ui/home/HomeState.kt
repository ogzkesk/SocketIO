package com.example.socket.ui.home

import com.example.socket.domain.result.ConnectionStatus

data class HomeState(
    val userNameText: String = "",
    val join: Boolean = false,
    val connectionStatus: ConnectionStatus? = null,
)
