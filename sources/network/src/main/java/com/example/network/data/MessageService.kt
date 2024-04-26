package com.example.network.data

import com.example.core.api.dto.Message

interface MessageService {

    suspend fun getAllMessages(): DataState<List<Message>>

    companion object {
        const val BASE_URL = "http://192.168.0.101:8080"
    }

    sealed class Endpoints(val url: String) {
        data object GetAllMessages: Endpoints("$BASE_URL/messages")
    }
}