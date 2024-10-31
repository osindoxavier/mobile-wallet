package com.comulynx.wallet.android.ui.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comulynx.wallet.android.data.model.Resource
import com.comulynx.wallet.android.data.model.entity.CustomerEntity
import com.comulynx.wallet.android.data.model.request.BaseRequest
import com.comulynx.wallet.android.data.model.response.AccountBalanceApiResponse
import com.comulynx.wallet.android.data.repository.CustomerRepository
import com.comulynx.wallet.android.data.repository.DataStoreRepository
import com.comulynx.wallet.android.domain.usecase.GetAccountBalanceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val customerRepository: CustomerRepository,
    private val getAccountBalanceUseCase: GetAccountBalanceUseCase
) : ViewModel() {
    private val TAG = "HomeViewModel"

    private lateinit var customerProfile: CustomerEntity


    private val _uiStateFlow = MutableStateFlow<UiStateFlow<CustomerEntity>>(UiStateFlow.Loading)
    val uiState = _uiStateFlow.asStateFlow()

    private val _balanceUIState = MutableSharedFlow<UiSharedFlow<AccountBalanceApiResponse>>()
    val balanceUIState = _balanceUIState.asSharedFlow()

    init {
        getCurrentCustomer()
    }

    private fun getCurrentCustomer() {
        dataStoreRepository.getCustomerId().onEach { customerId ->
            Log.d(TAG, "getCurrentCustomer: $customerId")
            if (customerId.isNotEmpty()) {
                getCustomerProfile(customerId = customerId)
            }
        }.launchIn(viewModelScope)
    }

    private fun getCustomerProfile(customerId: String) {
        viewModelScope.launch {
            _uiStateFlow.value = UiStateFlow.Loading
            customerRepository.getCustomerByCustomerId(customerId = customerId).catch { exception ->
                Log.e(TAG, "getCustomerProfile: error: ${exception.message}", exception)
                _uiStateFlow.value =
                    UiStateFlow.Error(errorMessage = exception.message ?: "Unexpected error occurred!")
            }.collect { profile ->
                _uiStateFlow.value = UiStateFlow.Success(data = profile)
                customerProfile = profile
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            dataStoreRepository.deleteCustomerId()
            customerRepository.deleteCustomerAccount(entity = customerProfile)
        }
    }

    fun getAccountBalance() {
        getAccountBalanceUseCase(request = BaseRequest(customerId = customerProfile.customerId))
            .onEach { resource ->
                when (resource) {
                    is Resource.Error -> {
                        Log.e(TAG, "getAccountBalance: ${resource.resourceError}")
                        _balanceUIState.emit(UiSharedFlow(errorMessage = resource.resourceError.message))
                    }

                    is Resource.Loading -> {
                        Log.d(TAG, "getAccountBalance: Loading")
                        _balanceUIState.emit(UiSharedFlow(isLoading = true))
                    }

                    is Resource.Success -> {
                        Log.d(TAG, "getAccountBalance: success::${resource.data}")
                        _balanceUIState.emit(UiSharedFlow(data = resource.data))
                    }
                }
            }.launchIn(viewModelScope)
    }
}