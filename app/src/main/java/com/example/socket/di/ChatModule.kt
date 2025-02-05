package com.example.socket.di

import com.example.socket.data.SessionRepositoryImpl
import com.example.socket.domain.session.SessionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ChatModule {

    @Provides
    @Singleton
    fun provideSessionRepository(): SessionRepository = SessionRepositoryImpl()
}