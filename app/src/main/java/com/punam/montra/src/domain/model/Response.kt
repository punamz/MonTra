package com.punam.montra.src.domain.model


data class Response<T>(
    val data: T,
    val code: Int,
    val message: String,
)
