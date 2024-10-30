package com.comulynx.wallet.android.ui.presentation.login

data class LoginUIState(
    val isLoading: Boolean = false,
    val errorMassage: String? = null,
    val success: String = ""
)
