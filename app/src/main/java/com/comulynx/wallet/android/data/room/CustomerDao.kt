package com.comulynx.wallet.android.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.comulynx.wallet.android.data.model.entity.CustomerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDao {
    @Upsert
    suspend fun saveUpdateCustomer(entity: CustomerEntity)

    @Query("SELECT * FROM user_account WHERE customerId = :customerId")
    fun getCustomerById(customerId: String): Flow<CustomerEntity>

    @Delete
    suspend fun deleteCustomer(entity: CustomerEntity)
}