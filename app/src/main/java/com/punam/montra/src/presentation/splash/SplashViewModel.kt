package com.punam.montra.src.presentation.splash

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.punam.montra.src.data.local_data.DataStoreDatabase
import com.punam.montra.src.data.local_data.PreferencesKey
import com.punam.montra.util.Routers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
class SplashViewModel @Inject constructor(
    private val storeDatabase: DataStoreDatabase,
) : ViewModel() {


    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<String> = mutableStateOf(Routers.Splash.name)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            val isOnboardCompleted = storeDatabase.readValue(
                PreferencesKey.onBoardingKey,
                default = false
            ).first()
            if (!isOnboardCompleted) {
                _startDestination.value = Routers.Onboard.name
            } else {
                val isLoggedIn = storeDatabase.readValue(
                    preferencesKey = PreferencesKey.userId,
                    default = ""
                ).first()
                _startDestination.value =
                    if (isLoggedIn.isNotEmpty()) Routers.Home.name else Routers.Login.name
            }
            _isLoading.value = false
        }
    }
}