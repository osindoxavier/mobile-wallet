package com.comulynx.wallet.android.ui.presentation.send_money

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.comulynx.wallet.android.core.Dimens
import com.comulynx.wallet.android.ui.presentation.components.AppTextView
import com.comulynx.wallet.android.ui.presentation.components.BaseTopBar

@Composable
fun SendMoneyScreen(
    state: SendMoneyFormState,
    onEvent: (SendMoneyEvent) -> Unit,
    onNavigationBack: () -> Boolean
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            BaseTopBar(navOnNavigation = {
                onNavigationBack()
            }, title = "Send Money")
        }
    ) { innerPadding ->
        SendMoneyContent(
            modifier = Modifier
                .padding(innerPadding),
            state = state,
            onEvent = onEvent
        )
    }

}

@Composable
fun SendMoneyContent(
    modifier: Modifier = Modifier,
    state: SendMoneyFormState,
    onEvent: (SendMoneyEvent) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(Dimens.mediumPadding)
    ) {
        SendMoneyForm(
            state = state,
            onEvent = onEvent
        )
        Spacer(modifier = Modifier.height(100.dp))
        Button(
            onClick = {
                onEvent(SendMoneyEvent.Submit)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Send",
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Composable
fun SendMoneyForm(
    modifier: Modifier = Modifier,
    state: SendMoneyFormState,
    onEvent: (SendMoneyEvent) -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        val (focusAccountTo, focusAmount) = remember { FocusRequester.createRefs() }
        val keyboardController = LocalSoftwareKeyboardController.current

        AppTextView(
            value = state.accountTo,
            onValueChange = {
                onEvent(SendMoneyEvent.AccountToChanged(it))
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.PermIdentity,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = "Account To",
                    color = MaterialTheme.colorScheme.onBackground
                )
            },
            placeholder = {
                Text(
                    text = "Enter Account To",
                    color = MaterialTheme.colorScheme.onBackground.copy(0.75f)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusAccountTo),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next, keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(onNext = { focusAmount.requestFocus() }),
            isError = state.accountToError != null
        )
        Spacer(modifier = Modifier.height(2.dp))
        if (state.accountToError != null) {
            Text(
                text = state.accountToError,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodySmall
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        AppTextView(
            value = state.amount,
            onValueChange = {
                onEvent(SendMoneyEvent.AmountChanged(it))
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Money,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = "Amount",
                    color = MaterialTheme.colorScheme.onBackground
                )

            },
            placeholder = {
                Text(
                    text = "Enter Amount",
                    color = MaterialTheme.colorScheme.onBackground.copy(0.75f)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusAmount),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number, imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
            }),
            isError = state.amountError != null
        )
        Spacer(modifier = Modifier.height(2.dp))
        if (state.amountError != null) {
            Text(
                text = state.amountError,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
