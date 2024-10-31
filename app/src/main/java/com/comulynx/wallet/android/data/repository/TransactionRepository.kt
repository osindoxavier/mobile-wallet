package com.comulynx.wallet.android.data.repository

import com.comulynx.wallet.android.data.model.request.BaseRequest
import com.comulynx.wallet.android.data.model.request.SendMoneyRequest
import com.comulynx.wallet.android.data.model.response.SendMoneyApiResponse
import com.comulynx.wallet.android.data.model.response.TransactionApiResponse
import retrofit2.Response

interface TransactionRepository {
    suspend fun getLast100Transaction(request: BaseRequest): Response<TransactionApiResponse>
    suspend fun sendMoney(request: SendMoneyRequest): Response<SendMoneyApiResponse>
}