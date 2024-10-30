package com.comulynx.wallet.android.ui.presentation.login

sealed class LoginEvent {
    data class CustomerIdChanged(val customerId: String) : LoginEvent()
    data class PinChanged(val pin: String) : LoginEvent()
    data object SubmitLogin : LoginEvent()
}