package com.example.socket.di

import com.example.socket.domain.service.SocketService
import com.example.socket.network.SocketServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ServiceModule {

    @Binds
    fun bindIService(serviceImpl: SocketServiceImpl): SocketService
}