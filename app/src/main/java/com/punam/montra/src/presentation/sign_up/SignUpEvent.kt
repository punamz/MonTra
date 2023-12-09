package com.punam.montra.src.presentation.sign_up

sealed class SignUpEvent {
    data class InputName(val value: String) : SignUpEvent()
    data class InputEmail(val value: String) : SignUpEvent()
    data class InputPassword(val value: String) : SignUpEvent()
    data object ToggleShowPassword : SignUpEvent()
    data object ToggleAcceptPolicy : SignUpEvent()
    data object SignUp : SignUpEvent()
}