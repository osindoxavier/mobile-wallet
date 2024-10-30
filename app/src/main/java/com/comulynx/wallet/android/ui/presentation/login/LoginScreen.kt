package com.comulynx.wallet.android.ui.presentation.login

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
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.comulynx.wallet.android.ui.presentation.components.AppTextView

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    state: LoginFormState,
    onEvent: (LoginEvent) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    )
    {
        SignInFormSection(
            state = state,
            onEvent = onEvent
        )

        Spacer(modifier = Modifier.height(100.dp))
        Button(
            onClick = {
                onEvent(LoginEvent.SubmitLogin)
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
                text = "Login",
                style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
            )
        }
    }

}

@Composable
private fun SignInFormSection(
    modifier: Modifier = Modifier,
    state: LoginFormState,
    onEvent: (LoginEvent) -> Unit
) {
    val (focusCustomerId, focusPin) = remember { FocusRequester.createRefs() }
    val keyboardController = LocalSoftwareKeyboardController.current
    var isPinVisible by remember { mutableStateOf(false) }


    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        AppTextView(
            value = state.customerId,
            onValueChange = {
                onEvent(LoginEvent.CustomerIdChanged(it))
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.PermIdentity,
                    contentDescription = "Password Icon"
                )
            },
            label = {
                Text(
                    text = "Customer Id",
                    color = MaterialTheme.colorScheme.onBackground
                )
            },
            placeholder = {
                Text(
                    text = "Enter Customer ID",
                    color = MaterialTheme.colorScheme.onBackground.copy(0.75f)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusCustomerId),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next, keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(onNext = { focusPin.requestFocus() }),
            isError = state.customerIdError != null
        )
        Spacer(modifier = Modifier.height(2.dp))
        if (state.customerIdError != null) {
            Text(
                text = state.customerIdError,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodySmall
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        AppTextView(
            value = state.pin,
            onValueChange = {
                onEvent(LoginEvent.PinChanged(it))
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Password Icon"
                )
            },
            trailingIcon = {
                IconButton(onClick = { isPinVisible = !isPinVisible }) {
                    Icon(
                        imageVector = if (isPinVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = "Password Toggle",
                        tint = Color.Gray
                    )
                }
            },
            label = {
                Text(
                    text = "Pin",
                    color = MaterialTheme.colorScheme.onBackground
                )

            },
            placeholder = {
                Text(
                    text = "Enter Pin",
                    color = MaterialTheme.colorScheme.onBackground.copy(0.75f)
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusPin),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
            }),
            visualTransformation = if (isPinVisible) VisualTransformation.None else PasswordVisualTransformation(),
            isError = state.pinError != null
        )
        Spacer(modifier = Modifier.height(2.dp))
        if (state.pinError != null) {
            Text(
                text = state.pinError,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }

}