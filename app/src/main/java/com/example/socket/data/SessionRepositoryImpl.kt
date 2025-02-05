package com.example.socket.data

import com.example.socket.domain.model.User
import com.example.socket.domain.session.SessionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SessionRepositoryImpl : SessionRepository {

    private val userData = MutableStateFlow<User?>(null)

    override fun stream(): StateFlow<User?> = userData.asStateFlow()

    override suspend fun setCurrentUser(user: User) {
        userData.emit(user)
    }
}