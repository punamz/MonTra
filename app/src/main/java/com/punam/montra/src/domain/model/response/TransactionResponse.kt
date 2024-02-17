package com.punam.montra.src.domain.model.response

import java.util.Date

data class TransactionResponse(
    val amount: Int,
    val category: CategoryResponse,
    val description: String,
    val id: String,
    val transactionAt: Date,
    val userId: String
)