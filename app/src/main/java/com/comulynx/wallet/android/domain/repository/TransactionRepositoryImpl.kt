package com.comulynx.wallet.android.domain.repository

import com.comulynx.wallet.android.data.model.request.BaseRequest
import com.comulynx.wallet.android.data.model.request.SendMoneyRequest
import com.comulynx.wallet.android.data.model.response.SendMoneyApiResponse
import com.comulynx.wallet.android.data.model.response.TransactionApiResponse
import com.comulynx.wallet.android.data.remote.MoneyWalletApi
import com.comulynx.wallet.android.data.repository.TransactionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val api: MoneyWalletApi
) : TransactionRepository {
    override suspend fun getLast100Transaction(request: BaseRequest): Response<TransactionApiResponse> {
        return withContext(Dispatchers.IO) {
            api.getTransactions(request = request)
        }
    }

    override suspend fun sendMoney(request: SendMoneyRequest): Response<SendMoneyApiResponse> {
        return withContext(Dispatchers.IO) {
            api.sendMoney(request = request)
        }
    }
}