package com.example.socket.di

import com.example.socket.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.socket.client.IO
import io.socket.client.Socket
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton
import javax.net.SocketFactory

@Module
@InstallIn(SingletonComponent::class)
object SocketModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().setLevel(
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    )

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ) = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .socketFactory(SocketFactory.getDefault().apply {
            createSocket()
        })
        .build()

    @Provides
    @Singleton
    fun provideSocketOptions(
        okHttpClient: OkHttpClient
    ): IO.Options = IO.Options.builder()
        .build()
        .apply {
            callFactory = okHttpClient
            webSocketFactory = okHttpClient
        }

    @Provides
    @Singleton
    fun provideSocket(
        options: IO.Options
    ): Socket = IO.socket("https://socket-io-chat.glitch.me/", options)

}