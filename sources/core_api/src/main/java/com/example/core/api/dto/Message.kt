package com.example.core.api.dto



data class Message(
    val username: String,
    val text: String,
    val timestamp: String,
    val image: Int? = null,
    val authorImage: Int? = null
)
