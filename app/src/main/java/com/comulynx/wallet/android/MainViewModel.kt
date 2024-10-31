package com.comulynx.wallet.android

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comulynx.wallet.android.data.repository.DataStoreRepository
import com.comulynx.wallet.android.ui.nav.NavRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {
    private val TAG = "MainViewModel"

    var splashCondition by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf<NavRoutes?>(null)
        private set

    init {
        getCurrentCustomerId()
    }

    private fun getCurrentCustomerId() {
        dataStoreRepository.getCustomerId().onEach { customerId ->
            Log.d(TAG, "getCurrentCustomerId: isEmpty::${customerId.isEmpty()}")
            startDestination =
                if (customerId.isEmpty()) NavRoutes.Login else NavRoutes.Home
            delay(300)
            splashCondition = false
        }.launchIn(viewModelScope)
    }
}