package com.punam.montra.src.domain.use_case.user

import com.punam.montra.src.domain.model.response.SignUpResponse
import com.punam.montra.src.domain.repository.UserRepository
import com.punam.montra.util.ViewState

class UserSignUp(
    private val repository: UserRepository,
) {

    suspend operator fun invoke(
        fullName: String,
        email: String,
        password: String,
    ): ViewState<SignUpResponse> {
        val res = repository.signUp(fullName = fullName, email = email, password = password)
        return res.fold(
            { ViewState.Error(it) },
            { ViewState.Success(it) }
        )
    }
}