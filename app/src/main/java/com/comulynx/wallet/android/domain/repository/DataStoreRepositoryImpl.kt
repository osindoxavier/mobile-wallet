package com.comulynx.wallet.android.domain.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.comulynx.wallet.android.core.Constants
import com.comulynx.wallet.android.data.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(private val dataStore: DataStore<Preferences>) :
    DataStoreRepository {
    override suspend fun saveCustomerId(customerId: String) {
        dataStore.edit { preference ->
            preference[Constants.CUSTOMER_ID] = customerId
        }
    }

    override fun getCustomerId(): Flow<String> {
        return dataStore.data.map { preference ->
            preference[Constants.CUSTOMER_ID] ?: ""
        }
    }

    override suspend fun deleteCustomerId() {
        dataStore.edit { preference ->
            preference.remove(Constants.CUSTOMER_ID)
        }
    }
}