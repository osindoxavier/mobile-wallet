package com.comulynx.wallet.android.ui.presentation.transaction.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.comulynx.wallet.android.data.model.response.Transaction

@Composable
fun TransactionsContent(modifier: Modifier, transactions: List<Transaction>) {
    if (transactions.isEmpty()) {
        EmptyTransactionSection(modifier = modifier)
    } else {
        TransactionsSection(
            transactions = transactions
        )
    }
}
