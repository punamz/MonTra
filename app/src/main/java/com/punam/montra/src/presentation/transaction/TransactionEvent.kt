package com.punam.montra.src.presentation.transaction

sealed class TransactionEvent {
    data object Refresh : TransactionEvent()
    data object GetMore : TransactionEvent()
}