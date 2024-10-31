package com.comulynx.wallet.android.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.comulynx.wallet.android.data.model.entity.CustomerEntity

@Database(
    entities = [CustomerEntity::class],
    version = 1,
    exportSchema = false
)
abstract class BaseRoomDataBase: RoomDatabase() {
    abstract fun customerDao(): CustomerDao
}