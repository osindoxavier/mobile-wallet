package com.comulynx.wallet.android.data.model.request


import com.google.gson.annotations.SerializedName


data class SignInRequest(
    val customerId: String,
    val pin: String
)