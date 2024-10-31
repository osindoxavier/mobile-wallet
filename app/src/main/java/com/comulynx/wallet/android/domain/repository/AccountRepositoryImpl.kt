package com.comulynx.wallet.android.domain.repository

import com.comulynx.wallet.android.data.model.request.BaseRequest
import com.comulynx.wallet.android.data.model.response.AccountBalanceApiResponse
import com.comulynx.wallet.android.data.remote.MoneyWalletApi
import com.comulynx.wallet.android.data.repository.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val api: MoneyWalletApi
):AccountRepository {
    override suspend fun getAccountBalance(request: BaseRequest): Response<AccountBalanceApiResponse> {
        return withContext(Dispatchers.IO){
            api.getAccountBalance(request=request)
        }
    }
}