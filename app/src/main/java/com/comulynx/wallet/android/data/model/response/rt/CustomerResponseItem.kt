package com.comulynx.wallet.android.data.model.response.rt


import com.google.gson.annotations.SerializedName

data class CustomerResponseItem(
    @SerializedName("customerId")
    val customerId: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("pin")
    val pin: String
)