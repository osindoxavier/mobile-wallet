package com.comulynx.wallet.android.data.remote

import com.comulynx.wallet.android.core.EndPoints
import com.comulynx.wallet.android.data.model.request.SignInRequest
import com.comulynx.wallet.android.data.model.response.SignInApiResponse
import com.comulynx.wallet.android.data.model.response.rt.CustomerResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST



interface AuthenticationApi {
    @POST(EndPoints.LOGIN)
    suspend fun customerLogin(@Body request: SignInRequest): Response<SignInApiResponse>
}