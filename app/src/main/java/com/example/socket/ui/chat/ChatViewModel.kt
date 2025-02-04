package com.example.socket.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socket.domain.service.SocketService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val socketService: SocketService
) : ViewModel() {

    private val mutableState = MutableStateFlow(ChatState())
    val state = mutableState.asStateFlow()

    fun onChatMessageChanged(message: String) {
        mutableState.update { it.copy(chatText = message) }
    }

    fun sendMessage() {
        viewModelScope.launch {
            // TODO send
        }
    }
}