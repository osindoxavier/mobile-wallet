package com.comulynx.wallet.android.ui.presentation.home

data class UiSharedFlow<T>(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val data: T? = null
)
