package com.punam.montra.src.presentation.transaction

import com.punam.montra.src.domain.model.response.TransactionResponse
import com.punam.montra.util.CategoryType
import com.punam.montra.util.OrderByType

data class TransactionState(
    val isLoading: Boolean = true,
    val isGettingMore: Boolean = false,
    val transactions: List<TransactionResponse> = emptyList(),
    val showFilterBottomSheet: Boolean = false,
    val showCategoryBottomSheet: Boolean = false,
    val categoryType: CategoryType? = null,
    val orderByType: OrderByType = OrderByType.Newest,
    val categories: List<String>? = null
)