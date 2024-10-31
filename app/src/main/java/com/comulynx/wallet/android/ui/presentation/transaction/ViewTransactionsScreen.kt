package com.comulynx.wallet.android.ui.presentation.transaction

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.comulynx.wallet.android.data.model.response.Transaction
import com.comulynx.wallet.android.ui.presentation.components.BaseTopBar
import com.comulynx.wallet.android.ui.presentation.transaction.components.TransactionsContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewTransactionsScreen(
    isLoading: Boolean,
    onRefresh: () -> Unit,
    transactions: List<Transaction>,
    onNavigationBack: () -> Unit
) {
    val refreshState = rememberPullToRefreshState()
    PullToRefreshBox(
        modifier = Modifier.fillMaxSize(),
        state = refreshState,
        isRefreshing = isLoading,
        onRefresh = onRefresh
    ) {
        Scaffold(
            topBar = {
                BaseTopBar(navOnNavigation = {
                    onNavigationBack()
                }, title = "View Transactions")
            }
        ) { innerPadding ->
            TransactionsContent(
                modifier = Modifier.padding(innerPadding),
                transactions = transactions
            )
        }

    }

}