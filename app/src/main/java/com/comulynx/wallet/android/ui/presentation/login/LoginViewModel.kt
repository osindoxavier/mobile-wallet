package com.comulynx.wallet.android.ui.presentation.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comulynx.wallet.android.data.mappers.toCustomerEntity
import com.comulynx.wallet.android.data.model.Resource
import com.comulynx.wallet.android.data.model.entity.CustomerEntity
import com.comulynx.wallet.android.data.model.request.SignInRequest
import com.comulynx.wallet.android.data.repository.CustomerRepository
import com.comulynx.wallet.android.data.repository.DataStoreRepository
import com.comulynx.wallet.android.data.validator.AuthenticationValidator
import com.comulynx.wallet.android.domain.usecase.LoginUseCase
import com.comulynx.wallet.android.ui.presentation.home.UiSharedFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val validator: AuthenticationValidator,
    private val dataStoreRepository: DataStoreRepository,
    private val customerRepository: CustomerRepository
) : ViewModel() {

    private val TAG = "LoginViewModel"

    var state by mutableStateOf(LoginFormState())
        private set

    private val _uiState = MutableSharedFlow<UiSharedFlow<String>>()
    val uiState = _uiState.asSharedFlow()


    fun onEvents(event: LoginEvent) {
        when (event) {
            is LoginEvent.CustomerIdChanged -> {
                state = state.copy(
                    customerId = event.customerId
                )
            }

            is LoginEvent.PinChanged -> {
                state = state.copy(
                    pin = event.pin
                )
            }

            is LoginEvent.SubmitLogin -> {
                validateLoginFormData()
            }
        }
    }

    private fun validateLoginFormData() {
        val customerIdResult = validator.executeCustomerId(customerId = state.customerId)
        val pinResult = validator.executePin(pin = state.pin)

        val hasError = listOf(
            customerIdResult,
            pinResult
        ).any { !it.successful }

        if (hasError) {
            state = state.copy(
                customerIdError = customerIdResult.errorMessage,
                pinError = pinResult.errorMessage
            )
            return
        }

        viewModelScope.launch {
            loginCustomer()
        }
    }

    private fun loginCustomer() {

        val request = SignInRequest(
            customerId = state.customerId,
            pin = state.pin
        )

        loginUseCase(
            request = request
        ).onEach { resource ->
            when (resource) {
                is Resource.Error -> {
                    Log.e(TAG, "loginCustomer: error::${resource.resourceError}")
                    _uiState.emit(
                        UiSharedFlow(
                            errorMessage = resource.resourceError.message
                        )
                    )
                }

                is Resource.Loading -> {
                    Log.d(TAG, "loginCustomer: Loading...")
                    _uiState.emit(
                        UiSharedFlow(
                            isLoading = true
                        )
                    )
                }

                is Resource.Success -> {
                    Log.d(TAG, "loginCustomer: success::")
                    val customer = resource.data
                    saveCustomerId(customerId = customer.customerId)
                    saveUpdateCustomerEntity(entity = customer.toCustomerEntity())
                    _uiState.emit(
                        UiSharedFlow(data = "Login successful")
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun saveCustomerId(customerId: String) {
        viewModelScope.launch {
            dataStoreRepository.saveCustomerId(customerId = customerId)
        }
    }

    private fun saveUpdateCustomerEntity(entity: CustomerEntity) {
        viewModelScope.launch {
            customerRepository.saveUpdateCustomer(entity = entity)
        }
    }

}