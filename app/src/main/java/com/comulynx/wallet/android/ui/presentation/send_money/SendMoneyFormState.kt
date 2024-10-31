package com.comulynx.wallet.android.ui.presentation.send_money

data class SendMoneyFormState(
    val amount: String = "",
    val amountError: String? = null,
    val accountTo: String = "",
    val accountToError: String? = null
)
