package com.punam.montra.src.data.repository

import arrow.core.Either
import com.punam.montra.src.data.remote.TransactionApi
import com.punam.montra.src.domain.model.response.ErrorResponse
import com.punam.montra.src.domain.model.response.FrequencyResponse
import com.punam.montra.src.domain.model.response.StatusCode
import com.punam.montra.src.domain.model.response.TransactionResponse
import com.punam.montra.src.domain.repository.TransactionRepository
import com.punam.montra.util.CategoryType
import com.punam.montra.util.FrequencyType
import com.punam.montra.util.OrderByType

class TransactionRepositoryImpl(
    private val api: TransactionApi,
) : TransactionRepository {
    override suspend fun getTransactions(
        userId: String,
        limit: Int,
        offset: Int,
        orderBy: OrderByType?,
        categoryType: CategoryType?,
        categoryId: List<String>?
    ): Either<ErrorResponse, List<TransactionResponse>> {

        val res = try {
            val params: MutableMap<String, Any?> = mutableMapOf()
            params["userId"] = userId
            params["limit"] = limit
            params["offset"] = offset
            if (orderBy != null)
                params["orderBy"] = orderBy.ordinal
            if (categoryType != null)
                params["categoryType"] = categoryType.ordinal
            if (categoryId != null)
                params["categoryId"] = categoryId
            api.getTransactions(queries = params)
        } catch (ex: Exception) {
            return Either.Left(ErrorResponse(message = "Error call api $ex"))
        }
        return if (res.statusCode == StatusCode.Success) Either.Right(res.data) else Either.Left(
            ErrorResponse(
                message = res.message,
                errorCode = res.errorCode
            )
        )
    }

    override suspend fun getFrequency(
        userId: String,
        frequencyType: FrequencyType,
        categoryType: CategoryType,
        timeZone: Int
    ): Either<ErrorResponse, FrequencyResponse> {
        val res = try {
            val params: MutableMap<String, Any?> = mutableMapOf()
            params["userId"] = userId
            params["frequencyType"] = frequencyType.ordinal
            params["timeZone"] = timeZone
            params["categoryType"] = categoryType.ordinal
            api.getFrequency(queries = params)
        } catch (ex: Exception) {
            return Either.Left(ErrorResponse(message = "Error call api $ex"))
        }
        return if (res.statusCode == StatusCode.Success) Either.Right(res.data) else Either.Left(
            ErrorResponse(
                message = res.message,
                errorCode = res.errorCode
            )
        )
    }
}