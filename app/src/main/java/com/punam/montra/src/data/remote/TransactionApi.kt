package com.punam.montra.src.data.remote

import com.punam.montra.src.domain.model.response.BaseResponse
import com.punam.montra.src.domain.model.response.CategoryResponse
import com.punam.montra.src.domain.model.response.FrequencyResponse
import com.punam.montra.src.domain.model.response.TransactionResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface TransactionApi {
    @GET("transaction/getTransactions")
    suspend fun getTransactions(
        @QueryMap queries: MutableMap<String, Any?>,
        @Query("categoriesId") categoriesId: List<String>?,
    ): BaseResponse<List<TransactionResponse>>

    @GET("transaction/getFrequency")
    suspend fun getFrequency(@QueryMap queries: MutableMap<String, Any?>): BaseResponse<FrequencyResponse>

    @GET("transaction/getCategories")
    suspend fun getCategories(@QueryMap queries: MutableMap<String, Any?>): BaseResponse<List<CategoryResponse>>

}
