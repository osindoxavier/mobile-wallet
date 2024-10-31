package com.comulynx.wallet.android.data.model.response


import com.google.gson.annotations.SerializedName

data class SendMoneyApiResponse(
    @SerializedName("response_message")
    val responseMessage: String,
    @SerializedName("response_status")
    val responseStatus: Boolean
)