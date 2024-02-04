package com.punam.montra.src.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patrykandpatrick.vico.core.entry.FloatEntry
import com.punam.montra.src.domain.model.response.TransactionResponse
import com.punam.montra.src.presentation.home.component.ChartType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state
    val charTypeList = listOf(ChartType.Today, ChartType.Week, ChartType.Month, ChartType.Year)

    init {
        getDataChart(ChartType.Today)
        getTransaction()
    }


    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.ChangedChartType -> changeChartType(event.type)
            is HomeEvent.Refresh -> {}
        }
    }


    private fun getDataChart(type: ChartType) {
        viewModelScope.launch {
            delay(2000)
            val data = when (type) {
                ChartType.Month -> listOf(
                    FloatEntry(0f, 10f),
                    FloatEntry(1f, 200f),
                    FloatEntry(2f, 15f),
                    FloatEntry(3f, 500f),
                    FloatEntry(4f, 200f),
                    FloatEntry(5f, 100f),
                    FloatEntry(6f, 200f),
                    FloatEntry(7f, 300f),
                    FloatEntry(8f, 100f),
                    FloatEntry(9f, 200f),
                    FloatEntry(10f, 300f),
                    FloatEntry(11f, 200f),
                    FloatEntry(12f, 500f),
                    FloatEntry(13f, 200f),
                    FloatEntry(14f, 300f),
                    FloatEntry(15f, 300f),
                    FloatEntry(16f, 100f),
                    FloatEntry(17f, 200f),
                    FloatEntry(18f, 500f),
                    FloatEntry(19f, 300f),
                    FloatEntry(20f, 500f),
                    FloatEntry(21f, 300f),
                    FloatEntry(22f, 400f),
                    FloatEntry(23f, 00f),
                    FloatEntry(24f, 100f),
                    FloatEntry(25f, 400f),
                    FloatEntry(26f, 00f),
                    FloatEntry(27f, 200f),
                    FloatEntry(28f, 150f),
                    FloatEntry(29f, 120f),
                    FloatEntry(30f, 300f),
                )

                ChartType.Today -> listOf(
                    FloatEntry(0f, 10f),
                    FloatEntry(1f, 200f),
                    FloatEntry(2f, 15f),
                    FloatEntry(3f, 500f),
                    FloatEntry(4f, 200f),
                    FloatEntry(5f, 100f),
                    FloatEntry(6f, 200f),
                    FloatEntry(7f, 300f),
                    FloatEntry(8f, 100f),
                    FloatEntry(9f, 200f),
                    FloatEntry(10f, 300f),
                    FloatEntry(11f, 200f),
                    FloatEntry(12f, 500f),
                    FloatEntry(13f, 200f),
                    FloatEntry(14f, 300f),
                    FloatEntry(15f, 300f),
                    FloatEntry(16f, 100f),
                    FloatEntry(17f, 100f),
                    FloatEntry(18f, 100f),
                    FloatEntry(19f, 100f),
                    FloatEntry(20f, 100f),
                    FloatEntry(21f, 100f),
                    FloatEntry(22f, 100f),
                    FloatEntry(23f, 100f),
                )

                ChartType.Week -> listOf(
                    FloatEntry(0f, 100f),
                    FloatEntry(1f, 00f),
                    FloatEntry(2f, 150f),
                    FloatEntry(3f, 200f),
                    FloatEntry(4f, 500f),
                    FloatEntry(5f, 300f),
                    FloatEntry(6f, 200f),
                )

                ChartType.Year -> listOf(
                    FloatEntry(0f, 100f),
                    FloatEntry(1f, 00f),
                    FloatEntry(2f, 150f),
                    FloatEntry(3f, 200f),
                    FloatEntry(4f, 500f),
                    FloatEntry(5f, 300f),
                    FloatEntry(6f, 200f),
                    FloatEntry(7f, 200f),
                    FloatEntry(8f, 200f),
                    FloatEntry(9f, 200f),
                    FloatEntry(10f, 200f),
                    FloatEntry(11f, 200f),
                )
            }
            _state.value = _state.value.copy(
                chartData = data,
                chartLoading = false,
            )
        }
    }

    private fun changeChartType(type: ChartType) {
        _state.value = _state.value.copy(
            chartType = type,
            chartData = emptyList(),
            chartLoading = true,
        )
        getDataChart(type)
    }

    private fun getTransaction() {
        val trans = listOf(
            TransactionResponse(
                type = "Income",
                category = "Salary",
                cost = 100,
                time = LocalDateTime.of(2024, 1, 1, 10, 0),
                note = "Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim velit mollit. Exercitation veniam consequat sunt nostrud amet."
            ),
        )
    }

}