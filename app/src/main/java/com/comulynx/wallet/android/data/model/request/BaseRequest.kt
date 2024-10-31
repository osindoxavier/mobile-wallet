package com.comulynx.wallet.android.data.model.request


import com.google.gson.annotations.SerializedName

data class BaseRequest(
    @SerializedName("customerId")
    val customerId: String
)