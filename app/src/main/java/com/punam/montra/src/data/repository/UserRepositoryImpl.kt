package com.punam.montra.src.data.repository

import arrow.core.Either
import com.punam.montra.src.data.remote.AuthenticationApi
import com.punam.montra.src.domain.model.request.LoginRequest
import com.punam.montra.src.domain.model.request.SignUpRequest
import com.punam.montra.src.domain.model.response.ErrorResponse
import com.punam.montra.src.domain.model.response.LoginResponse
import com.punam.montra.src.domain.model.response.SignUpResponse
import com.punam.montra.src.domain.model.response.StatusCode
import com.punam.montra.src.domain.repository.UserRepository

class UserRepositoryImpl(
    private val api: AuthenticationApi,
) : UserRepository {
    override suspend fun login(
        email: String,
        password: String,
    ): Either<ErrorResponse, LoginResponse> {
        val res = try {
            val req = LoginRequest(
                email = email,
                password = password
            )
            api.login(request = req)
        } catch (ex: Exception) {
            return Either.Left(ErrorResponse(message = "Error call api"))
        }
        return if (res.statusCode == StatusCode.Success) Either.Right(res.data) else Either.Left(
            ErrorResponse(
                message = res.message,
                errorCode = res.errorCode
            )
        )

    }

    override suspend fun signUp(
        fullName: String,
        email: String,
        password: String,
    ): Either<ErrorResponse, SignUpResponse> {
        val res = try {
            val req = SignUpRequest(
                fullName = fullName,
                email = email,
                password = password
            )
            api.registration(request = req)
        } catch (ex: Exception) {
            return Either.Left(ErrorResponse("Error call api"))
        }
        return if (res.statusCode == StatusCode.Success) Either.Right(res.data) else Either.Left(
            ErrorResponse(
                message = res.message,
                errorCode = res.errorCode
            )
        )
    }
}