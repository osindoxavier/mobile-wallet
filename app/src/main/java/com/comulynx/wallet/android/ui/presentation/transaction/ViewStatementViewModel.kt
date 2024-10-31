package com.comulynx.wallet.android.ui.presentation.transaction

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comulynx.wallet.android.data.model.Resource
import com.comulynx.wallet.android.data.model.request.BaseRequest
import com.comulynx.wallet.android.data.model.response.Transaction
import com.comulynx.wallet.android.data.repository.DataStoreRepository
import com.comulynx.wallet.android.domain.usecase.GetMiniStatementUseCase
import com.comulynx.wallet.android.ui.presentation.home.UiSharedFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ViewStatementViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val getMiniStatementUseCase: GetMiniStatementUseCase
) : ViewModel() {
    private val TAG = "ViewStatementViewModel"


    private val _uiStateFlow = MutableSharedFlow<UiSharedFlow<List<Transaction>>>()
    val uiState = _uiStateFlow.asSharedFlow()

    init {
        getCurrentCustomer()
    }


    fun getCurrentCustomer() {
        dataStoreRepository.getCustomerId().onEach { customerId ->
            Log.d(TAG, "getCurrentCustomer: $customerId")
            if (customerId.isNotEmpty()) {
                getMiniStatement(customerId=customerId)
            }
        }.launchIn(viewModelScope)
    }



    private fun getMiniStatement(customerId: String) {
        getMiniStatementUseCase
            .invoke(
                request = BaseRequest(customerId = customerId)
            )
            .onEach { resource ->
                when (resource) {
                    is Resource.Error -> {
                        Log.d(TAG, "getMiniStatement: error::${resource.resourceError}")
                        _uiStateFlow.emit(UiSharedFlow(errorMessage = resource.resourceError.message))
                    }

                    is Resource.Loading -> {
                        Log.d(TAG, "getMiniStatement: Loading...")
                        _uiStateFlow.emit(UiSharedFlow(isLoading = true))
                    }

                    is Resource.Success -> {
                        Log.d(TAG, "getMiniStatement: success:: ${resource.data.size} items")
                        _uiStateFlow.emit(UiSharedFlow(data = resource.data.toList()))
                    }
                }
            }.launchIn(viewModelScope)
    }
}