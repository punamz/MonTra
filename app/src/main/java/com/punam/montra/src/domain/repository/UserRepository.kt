package com.punam.montra.src.domain.repository

import arrow.core.Either
import com.punam.montra.src.domain.model.LoginResponse

interface UserRepository {
    suspend fun login(email: String, password: String): Either<String, LoginResponse>
}