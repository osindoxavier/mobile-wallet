package com.comulynx.wallet.android.data.model.request


import com.google.gson.annotations.SerializedName

data class SendMoneyRequest(
    @SerializedName("accountFrom")
    val accountFrom: String,
    @SerializedName("accountTo")
    val accountTo: String,
    @SerializedName("amount")
    val amount: Int,
    @SerializedName("customerId")
    val customerId: String
)