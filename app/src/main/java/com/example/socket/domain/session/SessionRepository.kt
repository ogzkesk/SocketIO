package com.example.socket.domain.session

import com.example.socket.domain.model.User
import kotlinx.coroutines.flow.StateFlow

interface SessionRepository {
    fun stream(): StateFlow<User?>
    suspend fun setCurrentUser(user: User)
}
