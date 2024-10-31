package com.comulynx.wallet.android.data.repository

import com.comulynx.wallet.android.data.model.entity.CustomerEntity
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {
    suspend fun saveUpdateCustomer(entity: CustomerEntity)
    fun getCustomerByCustomerId(customerId: String): Flow<CustomerEntity>
    suspend fun deleteCustomerAccount(entity: CustomerEntity)
}