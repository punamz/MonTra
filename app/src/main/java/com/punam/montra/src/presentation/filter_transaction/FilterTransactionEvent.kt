package com.punam.montra.src.presentation.filter_transaction

import com.punam.montra.util.CategoryType
import com.punam.montra.util.OrderByType

sealed class FilterTransactionEvent {
    data class ChangeOrderByType(val value: OrderByType) : FilterTransactionEvent()
    data class ChangeCategoryType(val value: CategoryType) : FilterTransactionEvent()
    data class UpdateCategories(val value: List<String>) : FilterTransactionEvent()
    data object Reset : FilterTransactionEvent()
    
}