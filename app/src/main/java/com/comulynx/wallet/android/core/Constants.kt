package com.comulynx.wallet.android.core

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey

object Constants {
    const val USER_PREFERENCE = "user-preference"
    val CUSTOMER_ID = stringPreferencesKey("customer_id")
    val LOCAL_DB = "mobile_wallet_db"
}