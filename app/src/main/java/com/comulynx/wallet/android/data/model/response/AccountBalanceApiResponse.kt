package com.comulynx.wallet.android.data.model.response


import com.google.gson.annotations.SerializedName

data class AccountBalanceApiResponse(
    @SerializedName("accountNo")
    val accountNo: String,
    @SerializedName("balance")
    val balance: Double
)