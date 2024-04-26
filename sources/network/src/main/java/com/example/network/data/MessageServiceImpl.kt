package com.example.network.data

import com.example.core.api.dto.Message
import com.example.network.data.dto.MessageDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get


class MessageServiceImpl(
    private val client: HttpClient
): MessageService {

    override suspend fun getAllMessages(): DataState<List<Message>> {
        return try {
            val data = client
                .get(MessageService.Endpoints.GetAllMessages.url)
                .body<List<MessageDto>>()
                .map { it.toMessage() }
            DataState.Success(data)
        } catch (e: Exception) {
            DataState.Error(UIComponent.Toast(
                e.localizedMessage ?: "Unknown Error"
            ))
        }
    }
}


