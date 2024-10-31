package com.comulynx.wallet.android.data.model.response


import com.google.gson.annotations.SerializedName

data class Transaction(
    @SerializedName("accountNo")
    val accountNo: String,
    @SerializedName("amount")
    val amount: Double,
    @SerializedName("balance")
    val balance: Double,
    @SerializedName("customerId")
    val customerId: String,
    @SerializedName("debitOrCredit")
    val debitOrCredit: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("transactionId")
    val transactionId: String,
    @SerializedName("transactionType")
    val transactionType: String
)