package com.comulynx.wallet.android.domain

fun showErrorMessage(errorCode: Int): String {
    return when (errorCode) {
        400 -> "Bad Request: The server could not understand the request due to invalid syntax."
        401 -> "Unauthorized: Authentication is required and has failed or has not been provided."
        403 -> "Forbidden: You do not have permission to access this resource."
        404 -> "Not Found: The requested resource could not be found on the server."
        405 -> "Method Not Allowed: The request method is not supported for this resource."
        409 -> "Conflict: The request could not be completed due to a conflict with the current state of the resource."
        429 -> "Too Many Requests: You have sent too many requests in a short period."
        500 -> "Internal Server Error: The server encountered an unexpected condition."
        502 -> "Bad Gateway: The server received an invalid response from the upstream server."
        503 -> "Service Unavailable: The server is currently unable to handle the request due to overload or maintenance."
        504 -> "Gateway Timeout: The server did not receive a timely response."
        else -> "An unexpected error occurred. Please try again later."
    }
}