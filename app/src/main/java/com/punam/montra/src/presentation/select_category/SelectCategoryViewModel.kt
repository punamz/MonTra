package com.punam.montra.src.presentation.select_category

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.punam.montra.src.data.local_data.DataStoreDatabase
import com.punam.montra.src.data.local_data.PreferencesKey
import com.punam.montra.src.domain.use_case.transaction.TransactionUseCase
import com.punam.montra.util.AppConstant
import com.punam.montra.util.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectCategoryViewModel @Inject constructor(
    private val storeDatabase: DataStoreDatabase,
    private val transactionUseCase: TransactionUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _state = mutableStateOf(SelectCategoryState())
    val state: State<SelectCategoryState> = _state
    private var limit = 10
    private var page = 0
    private var canLoadMore = true

    init {
        handleArg()
        getCategory()
    }

    private fun handleArg() {
        val argument = savedStateHandle.get<String>(AppConstant.SelectCategoryArgKey)
        val type = object : TypeToken<List<String>>() {}.type
        val categoriesSelected = Gson().fromJson<List<String>>(argument, type)
        _state.value = _state.value.copy(
            categoriesSelected = categoriesSelected
        )
    }

    fun onEvent(event: SelectCategoryEvent) {
        when (event) {
            SelectCategoryEvent.GetMore -> getMoreTransaction()
            SelectCategoryEvent.Refresh -> refreshData()
            is SelectCategoryEvent.SelectCategory -> selectCategory(event.value)
            is SelectCategoryEvent.UnselectCategory -> unselectCategory(event.value)
        }
    }

    private fun refreshData() {
        page = 0
        canLoadMore = true
        _state.value = _state.value.copy(
            isLoading = true,
            categories = emptyList()
        )
        getCategory()
    }

    private fun getMoreTransaction() {
        if (!canLoadMore) return
        if (_state.value.isGettingMore || _state.value.isLoading) return
        ++page
        getCategory()
    }

    private fun getCategory() {
        viewModelScope.launch {
            val userId = storeDatabase.readValue(PreferencesKey.userId, "").first()
            val result = transactionUseCase.categoryGet(
                userId = userId,
                limit = limit,
                offset = limit * page,
            )

            val categories = if (result is ViewState.Success)
                _state.value.categories + result.value else _state.value.categories

            _state.value = _state.value.copy(
                isLoading = false,
                isGettingMore = false,
                categories = categories,
            )
            if (result !is ViewState.Success) canLoadMore = false
            _state.value = _state.value.copy(
                isLoading = false,
                isGettingMore = false,
            )
        }
    }

    private fun unselectCategory(value: String) {
        val categoriesSelected = _state.value.categoriesSelected.toMutableList().apply {
            remove(value)
        }
        _state.value = _state.value.copy(
            categoriesSelected = categoriesSelected
        )
    }

    private fun selectCategory(value: String) {
        val categoriesSelected = _state.value.categoriesSelected.toMutableList().apply {
            add(value)
        }
        _state.value = _state.value.copy(
            categoriesSelected = categoriesSelected
        )
    }

}