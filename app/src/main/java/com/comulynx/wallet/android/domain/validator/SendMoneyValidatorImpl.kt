package com.comulynx.wallet.android.domain.validator

import com.comulynx.wallet.android.data.model.ValidationResult
import com.comulynx.wallet.android.data.validator.SendMoneyValidator

class SendMoneyValidatorImpl : SendMoneyValidator {
    override fun executeAmount(amount: String): ValidationResult {
        if (amount.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Amount cannot be empty!"
            )
        }

        if (amount.startsWith("0")) {
            return ValidationResult(
                successful = false,
                errorMessage = "Invalid amount!"
            )

        }

        return ValidationResult(
            successful = true
        )
    }

    override fun executeAccountTo(accountTo: String): ValidationResult {
        if (accountTo.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Enter the account number of the person you are sending to."
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}