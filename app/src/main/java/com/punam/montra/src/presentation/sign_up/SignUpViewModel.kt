package com.punam.montra.src.presentation.sign_up

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.punam.montra.R
import com.punam.montra.util.AppConstant
import com.punam.montra.util.UiText
import com.punam.montra.util.isEmail
import com.punam.montra.util.isPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {

    private val _state = mutableStateOf(SignUpState())
    val state: MutableState<SignUpState> = _state

    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.InputName -> updateNameInput(event.value)
            is SignUpEvent.InputEmail -> updateEmailInput(event.value)
            is SignUpEvent.InputPassword -> updatePasswordInput(event.value)
            SignUpEvent.ToggleShowPassword -> toggleShowPassword()
            SignUpEvent.ToggleAcceptPolicy -> toggleAcceptPolicy()
            SignUpEvent.SignUp -> signUp()
        }
    }

    private fun updateNameInput(value: String) {
        _state.value = _state.value.copy(
            nameInput = value,
            nameError = null,
        )
    }

    private fun updateEmailInput(value: String) {
        _state.value = _state.value.copy(
            emailInput = value,
            emailError = null,
        )
    }

    private fun updatePasswordInput(value: String) {
        _state.value = _state.value.copy(
            passwordInput = value,
            passwordError = null,
        )
    }


    private fun toggleShowPassword() {
        _state.value = _state.value.copy(isHidePassword = !_state.value.isHidePassword)
    }

    private fun toggleAcceptPolicy() {
        _state.value = _state.value.copy(acceptPolicy = !_state.value.acceptPolicy)
    }

    private fun signUp() {
        val isValid = validateValue();
        if (!isValid) return
        handleSignUp()
    }

    private fun validateValue(): Boolean {
        val nameValidationError = validateName(_state.value.nameInput)
        val emailValidationError = validateEmail(_state.value.emailInput)
        val passwordValidationError = validatePassword(_state.value.passwordInput)
        if (nameValidationError != null || emailValidationError != null || passwordValidationError != null) {
            _state.value = _state.value.copy(
                nameError = nameValidationError,
                emailError = emailValidationError,
                passwordError = passwordValidationError,
            )
            return false
        }
        return true
    }


    private fun validateName(name: String): UiText? {
        if (name.isEmpty()) return UiText.StringResource(R.string.name_empty_error)
        return null
    }

    private fun validateEmail(email: String): UiText? {
        if (email.isEmpty()) return UiText.StringResource(R.string.email_empty_error)
        if (!email.isEmail()) return UiText.StringResource(R.string.email_not_valid)
        return null
    }

    private fun validatePassword(password: String): UiText? {
        if (password.isEmpty()) return UiText.StringResource(R.string.password_empty_error)
        if (!password.isPassword()) return UiText.StringResource(
            R.string.password_not_valid,
            AppConstant.MIN_PASSWORD_LENGTH
        )
        return null
    }

    private fun handleSignUp() {
        // todo: handle sign up here
    }
}