package com.punam.montra.src.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun login(email: String, password: String): Flow<Boolean>
}