package com.comulynx.wallet.android.data.mappers

import com.comulynx.wallet.android.data.model.entity.CustomerEntity
import com.comulynx.wallet.android.data.model.response.SignInApiResponse

fun SignInApiResponse.toCustomerEntity(): CustomerEntity {
    return CustomerEntity(
        customerId = customerId,
        email = email,
        firstName = firstName,
        lastName = lastName,
        customerAccount = customerAccount,
    )
}