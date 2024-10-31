package com.comulynx.wallet.android.domain.repository

import com.comulynx.wallet.android.data.model.entity.CustomerEntity
import com.comulynx.wallet.android.data.repository.CustomerRepository
import com.comulynx.wallet.android.data.room.CustomerDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

class CustomerRepositoryImpl @Inject constructor(private val dao: CustomerDao) :
    CustomerRepository {
    override suspend fun saveUpdateCustomer(entity: CustomerEntity) {
        dao.saveUpdateCustomer(entity = entity)
    }

    override fun getCustomerByCustomerId(customerId: String): Flow<CustomerEntity> {
        return dao.getCustomerById(customerId = customerId).distinctUntilChanged()
    }

    override suspend fun deleteCustomerAccount(entity: CustomerEntity) {
        dao.deleteCustomer(entity = entity)
    }
}