package com.punam.montra.src.data.remote

import com.punam.montra.src.domain.model.LoginRequest
import com.punam.montra.src.domain.model.LoginResponse
import com.punam.montra.src.domain.model.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationApi {
    @POST("authaccount/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}