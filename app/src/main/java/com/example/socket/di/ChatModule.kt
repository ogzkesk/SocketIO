package com.example.socket.di

import com.example.socket.data.ChatSessionRepositoryImpl
import com.example.socket.domain.session.ChatSessionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ChatModule {

    @Binds
    fun bindChatRepository(
        chatRepositoryImpl: ChatSessionRepositoryImpl
    ): ChatSessionRepository
}