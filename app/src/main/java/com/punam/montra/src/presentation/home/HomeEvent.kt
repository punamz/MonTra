package com.punam.montra.src.presentation.home

import com.punam.montra.util.FrequencyType


sealed class HomeEvent {
    data class ChangedFrequencyType(val type: FrequencyType) : HomeEvent()
    data object Refresh : HomeEvent()
}