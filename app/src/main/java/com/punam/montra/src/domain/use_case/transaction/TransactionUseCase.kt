package com.punam.montra.src.domain.use_case.transaction

data class TransactionUseCase(
    val transactionGet: TransactionGet,
    val frequencyGet: FrequencyGet
)