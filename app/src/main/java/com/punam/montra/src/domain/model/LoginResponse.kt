package com.punam.montra.src.domain.model

data class LoginResponse(
    val email: String?,
    val id: Int?,
    val name: String?,
    val token: String?,
)