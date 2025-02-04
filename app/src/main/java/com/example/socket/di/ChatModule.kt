package com.example.socket.di

import com.example.socket.data.SessionRepositoryImpl
import com.example.socket.domain.session.SessionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ChatModule {

    @Binds
    fun bindChatRepository(
        chatRepositoryImpl: SessionRepositoryImpl
    ): SessionRepository
}