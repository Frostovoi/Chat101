package com.example.network.data


import com.example.core.api.dto.Message
import kotlinx.coroutines.flow.Flow

interface ChatSocketService {

    suspend fun initSession(username: String): DataState<Unit>

    suspend fun sendMessage(message: String)

    fun observeMessages(): Flow<Message>

    suspend fun closeSession()

    companion object {
        const val BASE_URL = "ws://192.168.0.101:8080"
    }

    sealed class Endpoints(val url: String) {
        data object ChatSocket: Endpoints("$BASE_URL/chat-socket")
    }
}
