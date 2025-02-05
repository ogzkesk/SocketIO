package com.example.socket.di

import com.example.socket.domain.service.SocketService
import com.example.socket.network.SocketServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.socket.client.Socket
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideSocketService(
        socket: Socket
    ): SocketService = SocketServiceImpl(socket)
}