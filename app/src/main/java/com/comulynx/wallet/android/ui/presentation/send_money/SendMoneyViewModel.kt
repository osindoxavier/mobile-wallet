package com.comulynx.wallet.android.ui.presentation.send_money

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comulynx.wallet.android.data.model.Resource
import com.comulynx.wallet.android.data.model.entity.CustomerEntity
import com.comulynx.wallet.android.data.model.request.SendMoneyRequest
import com.comulynx.wallet.android.data.repository.CustomerRepository
import com.comulynx.wallet.android.data.repository.DataStoreRepository
import com.comulynx.wallet.android.data.validator.SendMoneyValidator
import com.comulynx.wallet.android.domain.usecase.SendMoneyUseCase
import com.comulynx.wallet.android.ui.presentation.home.UiSharedFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SendMoneyViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val customerRepository: CustomerRepository,
    private val sendMoneyUseCase: SendMoneyUseCase,
    private val validator: SendMoneyValidator
) : ViewModel() {
    private val TAG = "SendMoneyViewModel"
    var state by mutableStateOf(SendMoneyFormState())
        private set

    private val _sendMoneyUIState = MutableSharedFlow<UiSharedFlow<String>>()
    val sendMoneyUIState = _sendMoneyUIState.asSharedFlow()

    private lateinit var customerProfile: CustomerEntity

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
            customerRepository.getCustomerByCustomerId(customerId = customerId).catch { exception ->
                Log.e(TAG, "getCustomerProfile: error: ${exception.message}", exception)
            }.collect { profile ->
                customerProfile = profile
            }
        }
    }

    fun onEvent(event: SendMoneyEvent) {
        when (event) {
            is SendMoneyEvent.AccountToChanged -> {
                state = state.copy(accountTo = event.accountTo)
            }

            is SendMoneyEvent.AmountChanged -> {
                state = state.copy(amount = event.amount)
            }

            is SendMoneyEvent.Submit -> {
                validateSendMoneyFormData()
            }
        }
    }

    private fun validateSendMoneyFormData() {
        val amountResult = validator.executeAmount(amount = state.amount)
        val accountToResult = validator.executeAccountTo(accountTo = state.accountTo)

        val hasError = listOf(
            amountResult,
            accountToResult
        ).any { !it.successful }

        if (hasError) {
            state = state.copy(
                amountError = amountResult.errorMessage,
                accountToError = accountToResult.errorMessage
            )
            return
        }

        viewModelScope.launch {
            sendMoney()
        }
    }

    private fun sendMoney() {
        val request = SendMoneyRequest(
            accountFrom = customerProfile.customerAccount,
            accountTo = state.accountTo,
            amount = state.amount.toInt(),
            customerId = customerProfile.customerId
        )
        sendMoneyUseCase(request = request).onEach { resource ->
            when (resource) {
                is Resource.Error -> {
                    Log.e(TAG, "sendMoney: ${resource.resourceError}")
                    _sendMoneyUIState.emit(UiSharedFlow(errorMessage = resource.resourceError.message))
                }

                is Resource.Loading -> {
                    Log.d(TAG, "sendMoney: Loading..")
                    _sendMoneyUIState.emit(UiSharedFlow(isLoading = true))
                }

                is Resource.Success -> {
                    Log.d(TAG, "sendMoney: success::${resource.data}")
                    _sendMoneyUIState.emit(UiSharedFlow(data = resource.data.responseMessage))
                }
            }
        }.launchIn(viewModelScope)

    }


}