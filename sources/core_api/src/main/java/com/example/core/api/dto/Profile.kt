package com.example.core.api.dto


data class Profile(
    val username: String,
    val email: String,
    val userImage: Int? = null
)