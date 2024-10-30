package com.comulynx.wallet.android.data.validator

import com.comulynx.wallet.android.data.model.ValidationResult

interface AuthenticationValidator {
    fun executeCustomerId(customerId: String): ValidationResult
    fun executePin(pin: String): ValidationResult
}