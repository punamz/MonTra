package com.punam.montra.src.presentation.home

import com.punam.montra.src.presentation.home.component.ChartType

sealed class HomeEvent {
    data class ChangedChartType(val type: ChartType) : HomeEvent()
    data object Refresh : HomeEvent()
}