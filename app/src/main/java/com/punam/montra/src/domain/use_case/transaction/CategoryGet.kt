package com.punam.montra.src.domain.use_case.transaction

import com.punam.montra.src.domain.model.response.CategoryResponse
import com.punam.montra.src.domain.repository.TransactionRepository
import com.punam.montra.util.ViewState

class CategoryGet(

    private val repository: TransactionRepository,
) {
    suspend operator fun invoke(
        userId: String,
        limit: Int,
        offset: Int,
    ): ViewState<List<CategoryResponse>> {
        val res = repository.getCategory(
            userId = userId,
            limit = limit,
            offset = offset
        )
        return res.fold(
            { ViewState.Error(it) },
            { ViewState.Success(it) }
        )
    }
}