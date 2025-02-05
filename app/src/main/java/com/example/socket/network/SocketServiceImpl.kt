package com.example.socket.network

import com.example.socket.domain.ext.fromJson
import com.example.socket.domain.ext.toJson
import com.example.socket.domain.model.User
import com.example.socket.domain.model.UserMessage
import com.example.socket.domain.result.ConnectionStatus
import com.example.socket.domain.service.SocketService
import io.socket.client.Socket
import org.json.JSONArray

class SocketServiceImpl(
    private val socket: Socket,
) : SocketService {

    override fun connect() {
        socket.connect()
    }

    override fun disconnect() {
        socket.disconnect()
    }

    override fun streamConnectionStatus(callback: (ConnectionStatus) -> Unit) {
        socket.on(Socket.EVENT_CONNECT) {
            callback.invoke(ConnectionStatus.CONNECTED)
        }
        socket.on(Socket.EVENT_CONNECT_ERROR) {
            callback.invoke(ConnectionStatus.FAILURE)
        }
        socket.on(Socket.EVENT_DISCONNECT) {
            callback.invoke(ConnectionStatus.DISCONNECTED)
        }
    }


    override fun join(user: User) {
        socket.emit("user", user.toJson())
    }

    override fun sendMessage(message: UserMessage) {
        socket.emit("message", message.toJson())
    }


    override fun streamUsers(callback: (List<User>) -> Unit) {
        socket.on("users") { args ->
            if (!args.isNullOrEmpty()) {
                val users = mutableListOf<User>()
                val array = (args[0] as JSONArray)
                for (index in 0..<array.length()) {
                    users.add((array[index] as String).fromJson())
                }
                callback.invoke(users)
            }
        }
    }


    override fun streamMessages(callback: (List<UserMessage>) -> Unit) {
        socket.on("messages") { args ->
            println("args:: ${args.toList()}")
            if (!args.isNullOrEmpty()) {
                val messages = mutableListOf<UserMessage>()
                val array = (args[0] as JSONArray)
                for (index in 0..<array.length()) {
                    messages.add((array[index] as String).fromJson())
                }
                callback.invoke(messages)
            }
        }
    }
}
