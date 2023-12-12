package com.punam.montra.src.domain.use_case.user

import com.punam.montra.src.domain.model.LoginResponse
import com.punam.montra.src.domain.repository.UserRepository
import com.punam.montra.util.ViewState

class UserLogin(
    private val repository: UserRepository,
) {

    suspend operator fun invoke(
        email: String,
        password: String,
    ): ViewState<LoginResponse> {
        val res = repository.login(email = email, password = password)
        return res.fold(
            { ViewState.Error(it) },
            { ViewState.Success(it) }
        )
    }
}
