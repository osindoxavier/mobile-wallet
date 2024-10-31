package com.comulynx.wallet.android.data.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
        suspend fun saveCustomerId(customerId:String)
        fun getCustomerId(): Flow<String>
        suspend fun deleteCustomerId()
}