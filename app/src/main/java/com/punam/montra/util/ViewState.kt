package com.punam.montra.util

sealed class ViewState<out T> {

    data object Loading : ViewState<Nothing>()
    data class Success<out T>(val value: T?) : ViewState<T>()
    data class Error<String>(val message: String) : ViewState<String>()
    data object NoContent : ViewState<Nothing>()
}
