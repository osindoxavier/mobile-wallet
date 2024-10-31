package com.comulynx.wallet.android.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_account")
data class CustomerEntity(
    @PrimaryKey
    val customerId: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val customerAccount: String
)
