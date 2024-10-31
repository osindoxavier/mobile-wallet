package com.comulynx.wallet.android.di


import com.comulynx.wallet.android.BuildConfig
import com.comulynx.wallet.android.data.remote.MoneyWalletApi
import com.comulynx.wallet.android.data.repository.AccountRepository
import com.comulynx.wallet.android.data.repository.AuthenticationRepository
import com.comulynx.wallet.android.data.repository.TransactionRepository
import com.comulynx.wallet.android.domain.repository.AccountRepositoryImpl
import com.comulynx.wallet.android.domain.repository.AuthenticationRepositoryImpl
import com.comulynx.wallet.android.domain.repository.TransactionRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY // Log request and response body
        return logging
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor) // Add the logging interceptor
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideMoneyWalletApi(retrofit: Retrofit): MoneyWalletApi {
        return retrofit.create(MoneyWalletApi::class.java)
    }

    @Singleton
    @Provides
    fun provideAuthenticationRepository(api: MoneyWalletApi): AuthenticationRepository =
        AuthenticationRepositoryImpl(api)

    @Singleton
    @Provides
    fun provideAccountRepository(api: MoneyWalletApi): AccountRepository =
        AccountRepositoryImpl(api = api)

    @Singleton
    @Provides
    fun provideTransactionRepository(api: MoneyWalletApi): TransactionRepository =
        TransactionRepositoryImpl(api = api)



}