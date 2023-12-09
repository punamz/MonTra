package com.punam.montra.src.domain.use_case.user

import com.punam.montra.src.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserLogin(
    private val repository: UserRepository,
) {

    operator fun invoke(
        email: String,
        password: String,
    ): Flow<Boolean> {
        return repository.login(email = email, password = password).map {
            true
        }
    }
}
