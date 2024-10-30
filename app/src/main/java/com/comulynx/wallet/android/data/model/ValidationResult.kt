package com.comulynx.wallet.android.data.model

data class ValidationResult(
    val successful:Boolean,
    val errorMessage:String?=null
)
