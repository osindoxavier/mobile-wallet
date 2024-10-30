package com.comulynx.wallet.android.domain.validator

import com.comulynx.wallet.android.data.model.ValidationResult
import com.comulynx.wallet.android.data.validator.AuthenticationValidator

class AuthenticationValidatorImpl :AuthenticationValidator {
    override fun executeCustomerId(customerId: String): ValidationResult {
        if (customerId.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Customer ID cannot be empty!"
            )
        }

        return ValidationResult(
            successful = true
        )
    }

    override fun executePin(pin: String): ValidationResult {
        if (pin.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Pin cannot be empty!"
            )
        }

        if (pin.length < 4){
            return ValidationResult(
                successful = false,
                errorMessage = "Pin cannot be less than 4 characters!"
            )
        }

        return ValidationResult(
            successful = true
        )
    }
}