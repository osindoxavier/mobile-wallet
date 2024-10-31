package com.comulynx.wallet.android.data.model.request


data class SignInRequest(
    val customerId: String,
    val pin: String
)