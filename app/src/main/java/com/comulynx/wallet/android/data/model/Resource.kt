package com.comulynx.wallet.android.data.model

sealed class Resource<out T>  {
    data object Loading : Resource<Nothing>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val resourceError: ResourceError) : Resource<Nothing>()
}