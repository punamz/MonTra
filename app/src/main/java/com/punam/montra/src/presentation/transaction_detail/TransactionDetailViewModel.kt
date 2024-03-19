package com.punam.montra.src.presentation.transaction_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.punam.montra.src.domain.model.response.TransactionResponse
import com.punam.montra.src.domain.use_case.transaction.TransactionUseCase
import com.punam.montra.util.AppConstant
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Base64
import javax.inject.Inject

@HiltViewModel
class TransactionDetailViewModel @Inject constructor(
    private val transactionUseCase: TransactionUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _state = mutableStateOf(TransactionDetailState())
    val state: State<TransactionDetailState> = _state

    init {
        handleArg()
    }

    fun onEvent(event: TransactionDetailEvent) {
        when (event) {
            TransactionDetailEvent.DeleteTransaction -> deleteTransaction()
            is TransactionDetailEvent.ToggleFilterBottomSheet -> toggleDeleteBottomSheet(event.value)
        }
    }

    private fun handleArg() {
        val encoderArg = savedStateHandle.get<String>(AppConstant.TRANSACTION_DETAIL_ARG_KEY)
        val argument = String(Base64.getUrlDecoder().decode(encoderArg))
        val type = object : TypeToken<TransactionResponse>() {}.type
        val transaction = Gson().fromJson<TransactionResponse>(argument, type)
        _state.value = _state.value.copy(
            transaction = transaction
        )
    }

    private fun deleteTransaction() {
        TODO("Not yet implemented")
    }

    private fun toggleDeleteBottomSheet(value: Boolean) {
        _state.value = _state.value.copy(
            showDeleteBottomSheet = value
        )
    }

}