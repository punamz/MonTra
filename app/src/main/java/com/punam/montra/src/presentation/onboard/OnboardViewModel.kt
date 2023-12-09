package com.punam.montra.src.presentation.onboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.punam.montra.src.data.local_data.DataStoreDatabase
import com.punam.montra.src.data.local_data.PreferencesKey
import com.punam.montra.util.OnboardContent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardViewModel @Inject constructor(
    private val storeDatabase: DataStoreDatabase,
) : ViewModel() {
    val page = listOf(
        OnboardContent.FirstPage,
        OnboardContent.SecondPage,
        OnboardContent.ThirdPage,
    )

    fun saveOnBoardingState(completed: Boolean) {
        viewModelScope.launch {
            storeDatabase.saveValue(PreferencesKey.onBoardingKey, value = completed)
        }
    }
}