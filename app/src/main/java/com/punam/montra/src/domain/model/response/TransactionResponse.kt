package com.punam.montra.src.domain.model.response

import java.time.LocalDateTime

class TransactionResponse(
    val category: String,
    val time: LocalDateTime,
    val note: String?,
    val cost: Int,
    val type: String,
)