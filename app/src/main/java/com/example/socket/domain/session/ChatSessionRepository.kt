package com.example.socket.domain.session

import com.example.socket.domain.model.User

interface ChatSessionRepository {
    fun getCurrentUser(): User?
    fun setCurrentUser(user: User)
}
