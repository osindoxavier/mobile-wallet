package com.comulynx.wallet.android.data.repository

import com.comulynx.wallet.android.data.model.request.BaseRequest
import com.comulynx.wallet.android.data.model.response.AccountBalanceApiResponse
import retrofit2.Response

interface AccountRepository {
    suspend fun getAccountBalance(request: BaseRequest):Response<AccountBalanceApiResponse>
}