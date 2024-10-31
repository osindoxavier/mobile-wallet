package com.comulynx.wallet.android.data.validator

import com.comulynx.wallet.android.data.model.ValidationResult

interface SendMoneyValidator {
    fun executeAmount(amount: String): ValidationResult
    fun executeAccountTo(accountTo: String): ValidationResult
}