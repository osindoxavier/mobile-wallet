package com.comulynx.wallet.android.ui.presentation.send_money

sealed class SendMoneyEvent {
    data class AmountChanged(val amount: String) : SendMoneyEvent()
    data class AccountToChanged(val accountTo: String) : SendMoneyEvent()
    data object Submit : SendMoneyEvent()
}