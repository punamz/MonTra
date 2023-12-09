package com.punam.montra.src.presentation.login

sealed class LoginEvent {
    data object ToggleShowPassword : LoginEvent()
    data class InputEmail(val value: String) : LoginEvent()
    data class InputPassword(val value: String) : LoginEvent()
    data object Login : LoginEvent()
}


