package com.punam.montra.src.domain.model.response


data class BaseResponse<T>(
    val data: T,
    val statusCode: Int,
    val errorCode: String,
    val message: String,
)

object StatusCode {
    const val Fail = -1
    const val NoData = 0
    const val Success = 1
}
