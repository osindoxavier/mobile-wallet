package com.comulynx.wallet.android.ui.presentation.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comulynx.wallet.android.data.model.entity.CustomerEntity
import com.comulynx.wallet.android.data.repository.CustomerRepository
import com.comulynx.wallet.android.data.repository.DataStoreRepository
import com.comulynx.wallet.android.ui.presentation.home.UiStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val customerRepository: CustomerRepository,
) : ViewModel() {

    private val TAG = "ProfileViewModel"
    private val _uiStateFlow = MutableStateFlow<UiStateFlow<CustomerEntity>>(UiStateFlow.Loading)
    val uiState = _uiStateFlow.asStateFlow()


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
            }
        }
    }
}