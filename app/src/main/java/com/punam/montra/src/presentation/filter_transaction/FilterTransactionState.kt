package com.punam.montra.src.presentation.filter_transaction

import com.punam.montra.util.CategoryType
import com.punam.montra.util.OrderByType

data class FilterTransactionState(
    val orderByType: OrderByType = OrderByType.Newest,
    val categoryType: CategoryType? = null,
    val categories: List<String> = emptyList(),
)