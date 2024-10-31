package com.comulynx.wallet.android.domain.usecase

import com.comulynx.wallet.android.data.model.Resource
import com.comulynx.wallet.android.data.model.ResourceError
import com.comulynx.wallet.android.data.model.request.BaseRequest
import com.comulynx.wallet.android.data.model.response.AccountBalanceApiResponse
import com.comulynx.wallet.android.data.repository.AccountRepository
import com.comulynx.wallet.android.domain.showErrorMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetAccountBalanceUseCase @Inject constructor(
    private val repository: AccountRepository
) {
    operator fun invoke(request: BaseRequest): Flow<Resource<AccountBalanceApiResponse>> = flow {
        emit(Resource.Loading)
        try {
            val response = repository.getAccountBalance(request = request)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    emit(Resource.Success(data = body))
                }
            } else {
                emit(
                    Resource.Error(
                        resourceError = ResourceError(
                            statusCode = response.code(),
                            message = showErrorMessage(errorCode = response.code())
                        )
                    )
                )
            }

        } catch (e: HttpException) {
            val resourceError =
                ResourceError(message = e.localizedMessage ?: "An unexpected error occurred!")
            emit(Resource.Error(resourceError = resourceError))
        } catch (e: IOException) {
            val resourceError = ResourceError(
                message = e.localizedMessage
                    ?: "Couldn't reach server, Check your internet connection!"
            )
            emit(Resource.Error(resourceError = resourceError))
        }

    }
}