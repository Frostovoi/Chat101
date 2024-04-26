package com.example.conversation

import androidx.compose.runtime.toMutableStateList
import com.example.core.api.dto.Message


class ConversationUiState (
    val channelName: String,
    val channelMembers: Int,
    initialMessages: List<Message>
) {

    private val _messages: MutableList<Message> = initialMessages.toMutableStateList()
    val messages: List<Message> = _messages

    fun addMessage(msg: Message) {
        _messages.add(0, msg)
    }

}



