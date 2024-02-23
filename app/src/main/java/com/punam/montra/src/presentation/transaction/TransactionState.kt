package com.punam.montra.src.presentation.transaction

import com.punam.montra.src.domain.model.response.TransactionResponse

data class TransactionState(
    val isLoading: Boolean = true,
    val isGettingMore: Boolean = false,
    val transactions: List<TransactionResponse> = emptyList(),
)