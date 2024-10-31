package com.comulynx.wallet.android.domain.repository

import com.comulynx.wallet.android.data.model.request.SignInRequest
import com.comulynx.wallet.android.data.model.response.SignInApiResponse
import com.comulynx.wallet.android.data.remote.MoneyWalletApi
import com.comulynx.wallet.android.data.repository.AuthenticationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val moneyWalletApi: MoneyWalletApi
) : AuthenticationRepository {
    override suspend fun customerLogin(request: SignInRequest): Response<SignInApiResponse> {
        return withContext(Dispatchers.IO) {
            moneyWalletApi.customerLogin(request = request)
        }
    }

}