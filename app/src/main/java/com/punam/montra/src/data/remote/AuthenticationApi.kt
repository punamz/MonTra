package com.punam.montra.src.data.remote

import com.punam.montra.src.domain.model.request.LoginRequest
import com.punam.montra.src.domain.model.request.SignUpRequest
import com.punam.montra.src.domain.model.response.BaseResponse
import com.punam.montra.src.domain.model.response.LoginResponse
import com.punam.montra.src.domain.model.response.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationApi {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): BaseResponse<LoginResponse>

    @POST("auth/registration")
    suspend fun registration(@Body request: SignUpRequest): BaseResponse<SignUpResponse>
}