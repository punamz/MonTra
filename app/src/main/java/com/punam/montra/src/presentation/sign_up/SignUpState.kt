package com.punam.montra.src.presentation.sign_up

import com.punam.montra.util.UiText

data class SignUpState(
    val nameInput: String = "",
    val nameError: UiText? = null,
    val emailInput: String = "",
    val emailError: UiText? = null,
    val passwordInput: String = "",
    val passwordError: UiText? = null,
    val isHidePassword: Boolean = true,
    val acceptPolicy: Boolean = false,
)