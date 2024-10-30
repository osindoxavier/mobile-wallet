package com.comulynx.wallet.android.ui.presentation.login

data class LoginFormState(
    val customerId: String = "",
    val customerIdError: String? = null,
    val pin: String = "",
    val pinError: String? = null
)
