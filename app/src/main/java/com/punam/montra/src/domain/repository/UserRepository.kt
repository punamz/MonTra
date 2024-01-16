package com.punam.montra.src.domain.repository

import arrow.core.Either
import com.punam.montra.src.domain.model.response.ErrorResponse
import com.punam.montra.src.domain.model.response.LoginResponse
import com.punam.montra.src.domain.model.response.SignUpResponse

interface UserRepository {
    suspend fun login(email: String, password: String): Either<ErrorResponse, LoginResponse>
    suspend fun signUp(
        fullName: String,
        email: String,
        password: String,
    ): Either<ErrorResponse, SignUpResponse>
}