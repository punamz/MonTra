package com.punam.montra.src.domain.model.response

data class SignUpResponse(
    val token: String?,
    val user: UserResponse?,
)