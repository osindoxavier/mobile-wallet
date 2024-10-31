package com.comulynx.wallet.android.ui.presentation.home

sealed class UiStateFlow<out T> {
    data object Loading : UiStateFlow<Nothing>()
    data class Success<out T>(val data: T) : UiStateFlow<T>()
    data class Error(val errorMessage: String) : UiStateFlow<Nothing>()
}