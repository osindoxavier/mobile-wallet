package com.comulynx.wallet.android.domain

fun showErrorMessage(errorCode: Int): String {
    return when (errorCode) {
        400 -> "There was a problem with your request. Please check and try again."
        401 -> "Invalid credentials,check your Pin. Please log in and try again."
        403 -> "You don’t have permission to access this. Contact support if you believe this is a mistake."
        404 -> "We couldn't find what you were looking for. Please check the address and try again."
        405 -> "This action isn't allowed. Please try a different option."
        409 -> "There’s a conflict with the current data. Please refresh and try again."
        429 -> "You’re doing that too often. Please wait a moment and try again."
        500 -> "Something went wrong on our end. Please try again later."
        502 -> "We're having trouble connecting to our servers. Please try again later."
        503 -> "Our service is temporarily unavailable. Please try again in a few minutes."
        504 -> "The connection timed out. Please check your internet and try again."
        else -> "An unexpected error occurred. Please try again later."
    }
}