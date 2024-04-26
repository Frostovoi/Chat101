package com.example.network.data.dto

import com.example.core.api.dto.Profile
import kotlinx.serialization.Serializable

@Serializable
data class ProfileDto(
    val username: String,
    val userImage: Int? = null,
    val email: String,
) {

    fun toProfile() : Profile = Profile(
        username = username,
        userImage = userImage,
        email = email
    )
}
