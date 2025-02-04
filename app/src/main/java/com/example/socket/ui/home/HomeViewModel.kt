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
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val socketService: SocketService,
    private val sessionRepository: SessionRepository
) : ViewModel() {
    private val mutableState = MutableStateFlow(HomeState())
    val state = mutableState.asStateFlow()

    init {
        viewModelScope.launch {
            socketService.connect { status ->
                mutableState.update {
                    it.copy(connectionStatus = status)
                }
            }
        }
    }

    fun userNameChanged(userName: String) {
        mutableState.update {
            it.copy(userNameText = userName)
        }
    }

    fun join() {
        viewModelScope.launch(Dispatchers.IO) {
            socketService.send("user", state.value.userNameText)
        }
    }

    private fun setLocalUser() {
        viewModelScope.launch {
            val user = User(
                id = "",
                name = state.value.userNameText,
                local = true
            )
            sessionRepository.setCurrentUser(user)
        }
    }
}
