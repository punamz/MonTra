package com.punam.montra.src.data.repository

import android.util.Log
import arrow.core.Either
import com.punam.montra.src.data.remote.AuthenticationApi
import com.punam.montra.src.domain.model.LoginRequest
import com.punam.montra.src.domain.model.LoginResponse
import com.punam.montra.src.domain.repository.UserRepository

class UserRepositoryImpl(
    private val api: AuthenticationApi,
) : UserRepository {
    override suspend fun login(email: String, password: String): Either<String, LoginResponse> {
        val res = try {
            val req = LoginRequest(
                email = email,
                password = password
            )
            api.login(request = req)
        } catch (ex: Exception) {
            Log.d("Nam nè", "login: $ex")
            return Either.Left("Error call api")
        }
        return if (res.code == 0) Either.Right(res.data) else Either.Left(res.message)

    }
}