package com.example.socket.ui.home

import androidx.lifecycle.ViewModel
import com.example.socket.domain.service.SocketService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val socketService: SocketService,
) : ViewModel() {
    private val mutableState = MutableStateFlow(HomeState())
    val state = mutableState.asStateFlow()

    fun userNameChanged(userName: String) {
        mutableState.update {
            it.copy(userNameText = userName)
        }
    }

    fun join() {
        socketService.connect()
    }
}
