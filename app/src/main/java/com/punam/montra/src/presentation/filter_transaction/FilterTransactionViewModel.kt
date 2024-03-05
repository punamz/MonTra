package com.punam.montra.src.presentation.filter_transaction

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.punam.montra.util.CategoryType
import com.punam.montra.util.OrderByType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FilterTransactionViewModel @Inject constructor() : ViewModel() {

    private val _state = mutableStateOf(FilterTransactionState())
    val state: State<FilterTransactionState> = _state
    fun onEvent(event: FilterTransactionEvent) {
        when (event) {
            is FilterTransactionEvent.ChangeCategoryType -> changeCategory(event.value)
            is FilterTransactionEvent.ChangeOrderByType -> changeOrderBy(event.value)
            is FilterTransactionEvent.UpdateCategories -> updateCategoriesSelected(event.value)
            FilterTransactionEvent.Reset -> resetFilter()
        }
    }

    private fun updateCategoriesSelected(value: List<String>) {
        _state.value = _state.value.copy(
            categories = value
        )
    }

    private fun resetFilter() {
        _state.value = _state.value.copy(
            categories = emptyList(),
            categoryType = null,
            orderByType = OrderByType.Newest,
        )
    }

    private fun changeOrderBy(value: OrderByType) {
        _state.value = _state.value.copy(
            orderByType = value
        )
    }

    private fun changeCategory(value: CategoryType) {
        _state.value = _state.value.copy(
            categoryType = value
        )
    }
}