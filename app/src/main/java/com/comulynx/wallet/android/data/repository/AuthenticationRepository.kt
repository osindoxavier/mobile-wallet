package com.comulynx.wallet.android.data.repository

import com.comulynx.wallet.android.data.model.request.SignInRequest
import com.comulynx.wallet.android.data.model.response.SignInApiResponse
import retrofit2.Response


interface AuthenticationRepository {
    suspend fun customerLogin(request: SignInRequest): Response<SignInApiResponse>
}