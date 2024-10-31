package com.comulynx.wallet.android.ui.nav

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.comulynx.wallet.android.data.model.response.AccountBalanceApiResponse
import com.comulynx.wallet.android.data.model.response.Transaction
import com.comulynx.wallet.android.ui.presentation.components.LoadingDialog
import com.comulynx.wallet.android.ui.presentation.components.MessageDialog
import com.comulynx.wallet.android.ui.presentation.home.HomeScreen
import com.comulynx.wallet.android.ui.presentation.home.HomeViewModel
import com.comulynx.wallet.android.ui.presentation.login.LoginScreen
import com.comulynx.wallet.android.ui.presentation.login.LoginViewModel
import com.comulynx.wallet.android.ui.presentation.profile.ProfileScreen
import com.comulynx.wallet.android.ui.presentation.profile.ProfileViewModel
import com.comulynx.wallet.android.ui.presentation.send_money.SendMoneyScreen
import com.comulynx.wallet.android.ui.presentation.send_money.SendMoneyViewModel
import com.comulynx.wallet.android.ui.presentation.transaction.ViewStatementScreen
import com.comulynx.wallet.android.ui.presentation.transaction.ViewStatementViewModel
import com.comulynx.wallet.android.ui.presentation.transaction.ViewTransactionsScreen
import com.comulynx.wallet.android.ui.presentation.transaction.ViewTransactionsViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

@Composable
fun BaseNavGraph(
    startDestination: NavRoutes,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<NavRoutes.Login> {
            val tag = "LoginScreen"
            val context = LocalContext.current
            val viewModel: LoginViewModel = hiltViewModel()
            var isLoading by remember {
                mutableStateOf(false)
            }

            LaunchedEffect(key1 = viewModel.uiState) {
                viewModel.uiState.collect { state ->
                    isLoading = state.isLoading
                    if (state.errorMessage != null) {
                        Log.e(tag, "BaseNavGraph: error::${state.errorMessage}")
                        Toast.makeText(context, state.errorMessage, Toast.LENGTH_SHORT).show()
                    }
                    if (state.data != null) {
                        Log.d(tag, "BaseNavGraph: ${state.data}")
                        Toast.makeText(context, state.data, Toast.LENGTH_SHORT).show()
                        navController.navigateToHome()
                    }

                }
            }
            if (isLoading) {
                LoadingDialog()
            }
            LoginScreen(state = viewModel.state, onEvent = viewModel::onEvents)
        }

        composable<NavRoutes.Home> {
            val context = LocalContext.current
            val scope = rememberCoroutineScope()
            val viewModel: HomeViewModel = hiltViewModel()
            val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

            var isLoading by remember {
                mutableStateOf(false)
            }

            var showBalanceDialog by remember {
                mutableStateOf(false)
            }

            var accountBalance by remember {
                mutableStateOf<AccountBalanceApiResponse?>(null)
            }

            LaunchedEffect(key1 = viewModel.balanceUIState) {
                viewModel.balanceUIState.collect { state ->
                    isLoading = state.isLoading
                    if (state.errorMessage != null) {
                        Toast.makeText(context, state.errorMessage, Toast.LENGTH_SHORT).show()
                    }
                    if (state.data != null) {
                        showBalanceDialog = true
                        accountBalance = state.data
                    }
                }
            }


            if (showBalanceDialog) {
                accountBalance?.let {
                    MessageDialog(
                        setShowDialog = {
                            showBalanceDialog = false
                        },
                        response = it
                    )
                }
            }
            if (isLoading) {
                LoadingDialog()
            }
            HomeScreen(
                uiStateFlow = uiState,
                onBalanceCheck = viewModel::getAccountBalance,
                navigateToSendMoney = {
                    navController.navigateToSendMoney()
                },
                navigateToViewStatement = {
                    navController.navigateToViewStatement()
                },
                navigateToViewTransactions = {
                    navController.navigateToViewTransactions()
                },
                navigateToProfile = {
                    navController.navigateToProfile()
                },
                logout = {
                    scope.launch {
                        viewModel.logout()
                        navController.navigateToLogin()
                    }
                }
            )
        }

        composable<NavRoutes.SendMoney> {
            val context = LocalContext.current
            val viewModel: SendMoneyViewModel = hiltViewModel()
            val state = viewModel.state

            var isLoading by remember {
                mutableStateOf(false)
            }

            LaunchedEffect(key1 = viewModel.sendMoneyUIState) {
                viewModel.sendMoneyUIState.collect { state ->
                    isLoading = state.isLoading
                    if (state.errorMessage != null) {
                        Toast.makeText(context, state.errorMessage, Toast.LENGTH_SHORT).show()
                    }
                    if (state.data != null) {
                        Toast.makeText(context, state.data, Toast.LENGTH_SHORT).show()
                        delay(3.seconds.inWholeMilliseconds)
                        navController.navigateToHome()
                    }
                }
            }

            if (isLoading) {
                LoadingDialog()
            }

            SendMoneyScreen(
                state = state,
                onEvent = viewModel::onEvent,
                onNavigationBack = {
                    navController.navigateUp()
                }
            )

        }

        composable<NavRoutes.ViewStatement> {
            val context = LocalContext.current
            val viewModel: ViewStatementViewModel = hiltViewModel()

            var isLoading by remember {
                mutableStateOf(false)
            }

            var transactions by remember {
                mutableStateOf<List<Transaction>>(emptyList())
            }

            LaunchedEffect(key1 = viewModel.uiState) {
                viewModel.uiState.collect { state ->
                    isLoading = state.isLoading
                    if (state.errorMessage != null) {
                        Toast.makeText(context, state.errorMessage, Toast.LENGTH_SHORT).show()
                    }
                    if (state.data != null) {
                        transactions = state.data
                    }
                }
            }

            ViewStatementScreen(
                onNavigationBack = {
                    navController.navigateUp()
                },
                isLoading = isLoading,
                onRefresh = viewModel::getCurrentCustomer,
                transactions = transactions
            )
        }

        composable<NavRoutes.ViewTransactions> {
            val context = LocalContext.current
            val viewModel: ViewTransactionsViewModel = hiltViewModel()

            var isLoading by remember {
                mutableStateOf(false)
            }

            var transactions by remember {
                mutableStateOf<List<Transaction>>(emptyList())
            }

            LaunchedEffect(key1 = viewModel.uiState) {
                viewModel.uiState.collect { state ->
                    isLoading = state.isLoading
                    if (state.errorMessage != null) {
                        Toast.makeText(context, state.errorMessage, Toast.LENGTH_SHORT).show()
                    }
                    if (state.data != null) {
                        transactions = state.data
                    }
                }
            }

            ViewTransactionsScreen(
                onNavigationBack = {
                    navController.navigateUp()
                },
                isLoading = isLoading,
                onRefresh = viewModel::getCurrentCustomer,
                transactions = transactions
            )
        }

        composable<NavRoutes.Profile> {
            val viewModel: ProfileViewModel = hiltViewModel()
            val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
            ProfileScreen(
                uiState = uiState,
                onNavigationBack = {
                    navController.navigateUp()
                }
            )
        }
    }


}

private fun NavController.navigateToLogin() {
    this.navigate(NavRoutes.Login) {
        popUpTo(this@navigateToLogin.graph.startDestinationId) { inclusive = true }
    }
}

private fun NavController.navigateToHome() {
    this.navigate(NavRoutes.Home) {
        popUpTo(this@navigateToHome.graph.startDestinationId) { inclusive = true }
    }
}

private fun NavController.navigateToSendMoney() {
    this.navigate(NavRoutes.SendMoney)
}

private fun NavController.navigateToViewStatement() {
    this.navigate(NavRoutes.ViewStatement)
}

private fun NavController.navigateToViewTransactions() {
    this.navigate(NavRoutes.ViewTransactions)
}

private fun NavController.navigateToProfile() {
    this.navigate(NavRoutes.Profile)
}
