package com.example.conversation

import com.example.core.api.dto.Message


data class ConversationState(
    val isLoading: Boolean = false,
    val channelName: String,
    val channelMembers: Int,
    val messages: List<Message> = emptyList(),

)
