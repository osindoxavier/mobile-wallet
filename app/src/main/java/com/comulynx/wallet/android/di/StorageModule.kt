package com.comulynx.wallet.android.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.comulynx.wallet.android.core.Constants
import com.comulynx.wallet.android.core.Constants.USER_PREFERENCE
import com.comulynx.wallet.android.data.repository.CustomerRepository
import com.comulynx.wallet.android.data.repository.DataStoreRepository
import com.comulynx.wallet.android.data.room.BaseRoomDataBase
import com.comulynx.wallet.android.data.room.CustomerDao
import com.comulynx.wallet.android.domain.repository.CustomerRepositoryImpl
import com.comulynx.wallet.android.domain.repository.DataStoreRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {
    @Singleton
    @Provides
    fun providePreferencesDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            migrations = listOf(SharedPreferencesMigration(appContext, USER_PREFERENCE)),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = { appContext.preferencesDataStoreFile(USER_PREFERENCE) }
        )
    }

    @Singleton
    @Provides
    fun provideSharedPreferenceRepository(dataStore: DataStore<Preferences>): DataStoreRepository {
        return DataStoreRepositoryImpl(dataStore = dataStore)
    }

    @Provides
    @Singleton
    fun provideLocalDataBase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, BaseRoomDataBase::class.java, Constants.LOCAL_DB
    )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()


    @Provides
    @Singleton
    fun provideUserAccountDao(db: BaseRoomDataBase) = db.customerDao()

    @Provides
    @Singleton
    fun providesCustomerRepository(dao: CustomerDao):CustomerRepository{
        return CustomerRepositoryImpl(dao=dao)
    }
}