package com.example.socket.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socket.domain.model.User
import com.example.socket.domain.service.SocketService
import com.example.socket.domain.session.SessionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val socketService: SocketService,
    private val sessionRepository: SessionRepository
) : ViewModel() {
    private val mutableState = MutableStateFlow(HomeState())
    val state = mutableState.asStateFlow()

    init {
        socketService.streamConnectionStatus { status ->
            mutableState.update {
                it.copy(connectionStatus = status)
            }
        }

        socketService.streamUsers { users ->
            viewModelScope.launch(Dispatchers.IO) {
                sessionRepository.stream().collect { currentUser ->
                    if (users.contains(currentUser)) {
                        mutableState.update {
                            it.copy(join = true)
                        }
                    }
                }
            }
        }

        socketService.connect()
    }

    fun userNameChanged(userName: String) {
        mutableState.update {
            it.copy(userNameText = userName)
        }
    }

    fun join() {
        viewModelScope.launch(Dispatchers.IO) {
            val user = User(
                name = state.value.userNameText,
                id = UUID.randomUUID().toString(),
            )
            socketService.join(user)
            sessionRepository.setCurrentUser(user)
        }
    }

    fun disconnect() {
        socketService.disconnect()
    }

    fun updateState(newState: HomeState) {
        mutableState.update { newState }
    }
}
