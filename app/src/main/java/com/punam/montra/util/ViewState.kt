package com.punam.montra.util

import com.punam.montra.src.domain.model.response.ErrorResponse

sealed class ViewState<out T> {

    data object Loading : ViewState<Nothing>()
    data class Success<out T>(val value: T) : ViewState<T>()
    data class Error<T>(val error: ErrorResponse) : ViewState<T>()
    data object NoContent : ViewState<Nothing>()
}
