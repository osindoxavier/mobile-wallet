package com.comulynx.wallet.android.data.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInApiResponse(
    @SerialName("customerId")
    val customerId: String,
    @SerialName("email")
    val email: String,
    @SerialName("firstName")
    val firstName: String,
    @SerialName("lastName")
    val lastName: String,
    @SerialName("customerAccount")
    val customerAccount: String
)