package com.punam.montra.src.presentation.transaction

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.punam.montra.src.data.local_data.DataStoreDatabase
import com.punam.montra.src.data.local_data.PreferencesKey
import com.punam.montra.src.domain.use_case.transaction.TransactionUseCase
import com.punam.montra.util.CategoryType
import com.punam.montra.util.OrderByType
import com.punam.montra.util.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val storeDatabase: DataStoreDatabase,
    private val transactionUseCase: TransactionUseCase,
) : ViewModel() {
    private val _state = mutableStateOf(TransactionState())
    val state: State<TransactionState> = _state
    private var limit = 10
    private var page = 0
    private var canLoadMore = true

    init {
        getTransaction()
    }

    fun onEvent(event: TransactionEvent) {
        when (event) {
            is TransactionEvent.GetMore -> getMoreTransaction()
            is TransactionEvent.Refresh -> refreshData()
            is TransactionEvent.ToggleFilterBottomSheet -> toggleFilterBottomSheet(event.value)
            is TransactionEvent.Filter -> updateFiler(
                event.categoryType,
                event.orderByType,
                event.categories
            )
        }
    }

    private fun updateFiler(
        categoryType: CategoryType?,
        orderByType: OrderByType,
        categories: List<String>
    ) {
        _state.value = _state.value.copy(
            categoryType = categoryType,
            orderByType = orderByType,
            categories = categories.ifEmpty { null }
        )
        refreshData()
    }


    private fun refreshData() {
        page = 0
        canLoadMore = true
        _state.value = _state.value.copy(
            isLoading = true,
            transactions = emptyList()
        )
        getTransaction()
    }

    private fun getMoreTransaction() {
        if (!canLoadMore) return
        if (_state.value.isGettingMore || _state.value.isLoading) return

        ++page
        getTransaction()
    }

    private fun getTransaction() {
        viewModelScope.launch {
            val userId = storeDatabase.readValue(PreferencesKey.userId, "").first()
            val result = transactionUseCase.transactionGet(
                userId = userId,
                limit = limit,
                offset = limit * page,
                orderBy = _state.value.orderByType,
                categoryType = _state.value.categoryType,
                categoriesId = _state.value.categories
            )

            val transactions = if (result is ViewState.Success)
                _state.value.transactions + result.value else _state.value.transactions

            _state.value = _state.value.copy(
                isLoading = false,
                isGettingMore = false,
                transactions = transactions,
            )
            if (result !is ViewState.Success) canLoadMore = false
        }
    }

    private fun toggleFilterBottomSheet(value: Boolean) {
        _state.value = _state.value.copy(
            showBottomSheet = value
        )
    }
}