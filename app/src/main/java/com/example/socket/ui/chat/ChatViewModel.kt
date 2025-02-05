package com.example.socket.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socket.domain.model.MessageType
import com.example.socket.domain.model.UserMessage
import com.example.socket.domain.service.SocketService
import com.example.socket.domain.session.SessionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val socketService: SocketService,
    private val sessionRepository: SessionRepository
) : ViewModel() {

    private val mutableState = MutableStateFlow(ChatState())
    val state = mutableState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            socketService.streamConnectionStatus { status ->
                mutableState.update { it.copy(connectionStatus = status) }
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            sessionRepository.stream().collect { user ->
                mutableState.update { it.copy(currentUser = user) }
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            socketService.streamMessages { messages ->
                mutableState.update { state ->
                    state.copy(
                        messages = messages.map { newMessage ->
                            if (newMessage.user.id == state.currentUser?.id) {
                                newMessage.copy(local = true)
                            } else {
                                newMessage
                            }
                        }
                    )
                }
            }
        }
    }

    fun onChatMessageChanged(message: String) {
        mutableState.update { it.copy(chatText = message) }
    }

    fun sendMessage() {
        viewModelScope.launch {
            val user = state.value.currentUser ?: return@launch
            socketService.sendMessage(
                UserMessage(
                    message = state.value.chatText,
                    user = user,
                    timestamp = System.currentTimeMillis(),
                    type = MessageType.DEFAULT,
                )
            )
            onChatMessageChanged("")
        }
    }
}