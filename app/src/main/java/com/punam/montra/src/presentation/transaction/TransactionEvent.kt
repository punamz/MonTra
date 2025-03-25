package com.punam.montra.src.presentation.transaction

import com.punam.montra.util.CategoryType
import com.punam.montra.util.OrderByType

sealed class TransactionEvent {
    data object Refresh : TransactionEvent()
    data object GetMore : TransactionEvent()
    data class ToggleFilterBottomSheet(val value: Boolean) : TransactionEvent()
    data class ToggleCategoryBottomSheet(val value: Boolean) : TransactionEvent()
    data class Filter(
        val categoryType: CategoryType?,
        val orderByType: OrderByType,
        val categories: List<String>
    ) : TransactionEvent()
}