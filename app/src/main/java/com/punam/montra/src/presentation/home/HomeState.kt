package com.punam.montra.src.presentation.home

import com.patrykandpatrick.vico.core.entry.FloatEntry
import com.punam.montra.src.domain.model.response.TransactionResponse
import com.punam.montra.src.presentation.home.component.ChartType

data class HomeState(
    val avatarUrl: String? = null,
    val balance: Int = 0,
    val income: Int = 0,
    val expenses: Int = 0,
    val chartData: List<FloatEntry> = emptyList(),
    val chartLoading: Boolean = true,
    val chartType: ChartType = ChartType.Today,
    val transactionHistory: List<TransactionResponse> = emptyList(),
)