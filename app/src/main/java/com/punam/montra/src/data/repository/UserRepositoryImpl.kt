package com.punam.montra.src.data.repository

import com.punam.montra.src.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl : UserRepository {
    override fun login(email: String, password: String): Flow<Boolean> {
        TODO("Not yet implemented")
    }
}