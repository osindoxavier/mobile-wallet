package com.comulynx.wallet.android.ui.presentation.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.comulynx.wallet.android.data.model.entity.CustomerEntity
import com.comulynx.wallet.android.ui.presentation.components.BasePreview
import com.comulynx.wallet.android.ui.presentation.home.components.GreetingSection
import com.comulynx.wallet.android.ui.presentation.home.components.PhoneMenuSection
import com.comulynx.wallet.android.ui.theme.MobileWalletTheme

@Composable
fun HomeScreen(
    uiStateFlow: UiStateFlow<CustomerEntity>,
    onBalanceCheck: () -> Unit,
    navigateToSendMoney: () -> Unit,
    navigateToViewStatement: () -> Unit,
    navigateToViewTransactions: () -> Unit,
    navigateToProfile: () -> Unit,
    logout: () -> Unit
) {
    val tag = "HomeScreen"

    Box(modifier = Modifier.fillMaxSize()) {
        when (uiStateFlow) {
            is UiStateFlow.Error -> {
                Log.e(tag, "HomeScreen: error::${uiStateFlow.errorMessage}")
                Column(
                    modifier = Modifier.align(alignment = Alignment.Center),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = uiStateFlow.errorMessage,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }

            is UiStateFlow.Loading -> {
                Log.d(tag, "HomeScreen: Loading...")
            }

            is UiStateFlow.Success -> {
                val profile = uiStateFlow.data
                HomePhoneLayout(
                    profile = profile,
                    onBalanceCheck = onBalanceCheck,
                    navigateToSendMoney = navigateToSendMoney,
                    navigateToViewStatement = navigateToViewStatement,
                    navigateToViewTransactions = navigateToViewTransactions,
                    navigateToProfile = navigateToProfile,
                    logout = logout
                )
            }
        }
    }

}

@Composable
fun HomePhoneLayout(
    profile: CustomerEntity,
    onBalanceCheck: () -> Unit,
    navigateToSendMoney: () -> Unit,
    navigateToViewStatement: () -> Unit,
    navigateToViewTransactions: () -> Unit,
    navigateToProfile: () -> Unit,
    logout: () -> Unit
) {

    Scaffold(topBar = {
        GreetingSection(username = profile.firstName)
    }) { innerPadding ->
        HomeContent(
            modifier = Modifier
                .padding(innerPadding),
            onBalanceCheck = onBalanceCheck,
            navigateToSendMoney = navigateToSendMoney,
            navigateToViewStatement = navigateToViewStatement,
            navigateToViewTransactions = navigateToViewTransactions,
            navigateToProfile = navigateToProfile,
            logout = logout
        )
    }
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    onBalanceCheck: () -> Unit,
    navigateToSendMoney: () -> Unit,
    navigateToViewStatement: () -> Unit,
    navigateToViewTransactions: () -> Unit,
    navigateToProfile: () -> Unit,
    logout: () -> Unit
) {
    PhoneMenuSection(
        modifier = modifier,
        onBalanceCheck = onBalanceCheck,
        navigateToSendMoney = navigateToSendMoney,
        navigateToViewStatement = navigateToViewStatement,
        navigateToViewTransactions = navigateToViewTransactions,
        navigateToProfile = navigateToProfile,
        logout = logout
    )
}

@BasePreview
@Composable
private fun HomeScreenPreview(modifier: Modifier = Modifier) {
    val state = UiStateFlow.Success<CustomerEntity>(
        data = CustomerEntity(
            customerId = "test",
            email = "test",
            firstName = "test",
            lastName = "test",
            customerAccount = "test"
        )
    )
    MobileWalletTheme {
        Surface {
            HomeScreen(
                uiStateFlow = state,
                onBalanceCheck = {},
                navigateToSendMoney = {},
                navigateToViewStatement = {},
                navigateToViewTransactions = {},
                navigateToProfile = {},
                logout = {}
            )
        }
    }

}




