package com.example.socket.data

import com.example.socket.domain.model.User
import com.example.socket.domain.session.ChatSessionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class ChatSessionRepositoryImpl @Inject constructor(

) : ChatSessionRepository {

    private val userData = MutableStateFlow<User?>(null)

    override fun getCurrentUser(): User? = this.userData.value

    override fun setCurrentUser(user: User) {
        this.userData.update { user }
    }
}