package com.punam.montra.src.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.patrykandpatrick.vico.core.entry.FloatEntry
import com.punam.montra.src.data.local_data.DataStoreDatabase
import com.punam.montra.src.data.local_data.PreferencesKey
import com.punam.montra.src.domain.use_case.transaction.TransactionUseCase
import com.punam.montra.util.CategoryType
import com.punam.montra.util.FrequencyType
import com.punam.montra.util.OrderByType
import com.punam.montra.util.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.TimeZone
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val storeDatabase: DataStoreDatabase,
    private val transactionUseCase: TransactionUseCase,
) : ViewModel() {
    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state
    val charTypeList =
        listOf(FrequencyType.Today, FrequencyType.Week, FrequencyType.Month, FrequencyType.Year)

    init {
        getDataChart(FrequencyType.Today)
        getTransaction()
    }


    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.ChangedFrequencyType -> changeFrequencyType(event.type)
            is HomeEvent.Refresh -> {}
        }
    }


    private fun getDataChart(type: FrequencyType) {
        viewModelScope.launch {

            val userId = storeDatabase.readValue(PreferencesKey.userId, "").first()

            val currentOffsetHours = TimeZone.getDefault().rawOffset / (1000 * 60 * 60)
            val result = transactionUseCase.frequencyGet(
                userId = userId,
                categoryType = CategoryType.Expenses,
                frequencyType = type,
                timeZone = currentOffsetHours
            )
            var data = emptyList<FloatEntry>()
            if (result is ViewState.Success) {
                data = when (type) {
                    FrequencyType.Month -> if (result.value.frequency.size < 28 || result.value.frequency.size > 31)
                        emptyList()
                    else result.value.frequency.mapIndexed { index, element ->
                        FloatEntry(
                            index.toFloat(),
                            element.toFloat()
                        )
                    }

                    FrequencyType.Today -> if (result.value.frequency.size != 24)
                        emptyList()
                    else
                        result.value.frequency.mapIndexed { index, element ->
                            FloatEntry(
                                index.toFloat(),
                                element.toFloat()
                            )
                        }

                    FrequencyType.Week -> if (result.value.frequency.size != 7)
                        emptyList()
                    else
                        result.value.frequency.mapIndexed { index, element ->
                            FloatEntry(
                                index.toFloat(),
                                element.toFloat()
                            )
                        }


                    FrequencyType.Year -> if (result.value.frequency.size != 12)
                        emptyList()
                    else
                        result.value.frequency.mapIndexed { index, element ->
                            FloatEntry(
                                index.toFloat(),
                                element.toFloat()
                            )
                        }
                }

            }
            _state.value = _state.value.copy(
                chartData = data,
                chartLoading = false,
            )

        }
    }

    private fun changeFrequencyType(type: FrequencyType) {
        _state.value = _state.value.copy(
            frequencyType = type,
            chartData = emptyList(),
            chartLoading = true,
        )
        getDataChart(type)
    }

    private fun getTransaction() {
        viewModelScope.launch {
            val userId = storeDatabase.readValue(PreferencesKey.userId, "").first()
            val result = transactionUseCase.transactionGet(
                userId = userId,
                limit = 5,
                offset = 0,
                orderBy = OrderByType.Newest,
            )
            if (result is ViewState.Success)
                _state.value = _state.value.copy(
                    transactionHistory = result.value
                )
        }
    }

}