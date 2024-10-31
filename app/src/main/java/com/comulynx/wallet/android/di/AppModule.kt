package com.comulynx.wallet.android.di

import com.comulynx.wallet.android.data.validator.AuthenticationValidator
import com.comulynx.wallet.android.data.validator.SendMoneyValidator
import com.comulynx.wallet.android.domain.validator.AuthenticationValidatorImpl
import com.comulynx.wallet.android.domain.validator.SendMoneyValidatorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideAuthenticationValidator(): AuthenticationValidator = AuthenticationValidatorImpl()

    @Singleton
    @Provides
    fun provideSendMoneyValidator(): SendMoneyValidator= SendMoneyValidatorImpl()


}