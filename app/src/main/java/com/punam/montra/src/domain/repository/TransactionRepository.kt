package com.punam.montra.src.domain.repository

import arrow.core.Either
import com.punam.montra.src.domain.model.response.CategoryResponse
import com.punam.montra.src.domain.model.response.ErrorResponse
import com.punam.montra.src.domain.model.response.FrequencyResponse
import com.punam.montra.src.domain.model.response.TransactionResponse
import com.punam.montra.util.CategoryType
import com.punam.montra.util.FrequencyType
import com.punam.montra.util.OrderByType

interface TransactionRepository {
    suspend fun getTransactions(
        userId: String,
        limit: Int,
        offset: Int,
        orderBy: OrderByType? = null,
        categoryType: CategoryType? = null,
        categoriesId: List<String>? = null
    ): Either<ErrorResponse, List<TransactionResponse>>

    suspend fun getFrequency(
        userId: String,
        frequencyType: FrequencyType,
        categoryType: CategoryType = CategoryType.Expenses,
        timeZone: Int = 0
    ): Either<ErrorResponse, FrequencyResponse>

    suspend fun getCategory(
        userId: String,
        limit: Int,
        offset: Int,
    ): Either<ErrorResponse, List<CategoryResponse>>
}