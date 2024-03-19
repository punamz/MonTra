package com.punam.montra.src.presentation.transaction_detail

sealed class TransactionDetailEvent {
    data object DeleteTransaction : TransactionDetailEvent()
    data class ToggleFilterBottomSheet(val value: Boolean) : TransactionDetailEvent()

}