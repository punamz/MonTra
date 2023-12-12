package com.punam.montra.util

sealed class ViewState<out T> {

    data object Loading : ViewState<Nothing>()
    data class Success<out T>(val value: T?) : ViewState<T>()
    data class Error<T>(val message: String) : ViewState<T>()
    data object NoContent : ViewState<Nothing>()
}
