package com.comulynx.wallet.android.data.remote

import com.comulynx.wallet.android.core.EndPoints
import com.comulynx.wallet.android.data.model.request.BaseRequest
import com.comulynx.wallet.android.data.model.request.SendMoneyRequest
import com.comulynx.wallet.android.data.model.request.SignInRequest
import com.comulynx.wallet.android.data.model.response.AccountBalanceApiResponse
import com.comulynx.wallet.android.data.model.response.SendMoneyApiResponse
import com.comulynx.wallet.android.data.model.response.SignInApiResponse
import com.comulynx.wallet.android.data.model.response.TransactionApiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface MoneyWalletApi {
    @POST(EndPoints.LOGIN)
    suspend fun customerLogin(@Body request: SignInRequest): Response<SignInApiResponse>

    @POST(EndPoints.BALANCE)
    suspend fun getAccountBalance(@Body request: BaseRequest): Response<AccountBalanceApiResponse>
    @POST(EndPoints.TRANSACTIONS)
    suspend fun getTransactions(@Body request: BaseRequest): Response<TransactionApiResponse>
    @POST(EndPoints.SEND_MONEY)
    suspend fun sendMoney(@Body request: SendMoneyRequest):Response<SendMoneyApiResponse>

}