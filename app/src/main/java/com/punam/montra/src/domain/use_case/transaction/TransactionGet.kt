package com.punam.montra.src.domain.use_case.transaction

import com.punam.montra.src.domain.model.response.TransactionResponse
import com.punam.montra.src.domain.repository.TransactionRepository
import com.punam.montra.util.CategoryType
import com.punam.montra.util.OrderByType
import com.punam.montra.util.ViewState

class TransactionGet(
    private val repository: TransactionRepository,
) {
    suspend operator fun invoke(
        userId: String,
        limit: Int,
        offset: Int,
        orderBy: OrderByType? = null,
        categoryType: CategoryType? = null,
        categoriesId: List<String>? = null
    ): ViewState<List<TransactionResponse>> {
        val res = repository.getTransactions(
            userId = userId,
            limit = limit,
            offset = offset,
            orderBy = orderBy,
            categoryType = categoryType,
            categoriesId = categoriesId
        )
        return res.fold(
            { ViewState.Error(it) },
            { ViewState.Success(it) }
        )
    }
}