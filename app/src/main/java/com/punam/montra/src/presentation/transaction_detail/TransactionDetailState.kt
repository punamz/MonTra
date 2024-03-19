package com.punam.montra.src.presentation.transaction_detail

import com.punam.montra.src.domain.model.response.TransactionResponse

data class TransactionDetailState(
    val isLoading: Boolean = true,
    val transaction: TransactionResponse? = null,
    val showDeleteBottomSheet: Boolean = false,
)
