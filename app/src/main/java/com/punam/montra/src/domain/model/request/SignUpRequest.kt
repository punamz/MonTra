package com.punam.montra.src.domain.model.request

data class SignUpRequest(
    val fullName: String,
    val email: String,
    val password: String,
)
