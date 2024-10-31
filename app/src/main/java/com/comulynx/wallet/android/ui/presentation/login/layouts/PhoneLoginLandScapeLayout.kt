package com.comulynx.wallet.android.ui.presentation.login.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fingerprint
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.comulynx.wallet.android.core.Dimens
import com.comulynx.wallet.android.ui.presentation.login.LoginEvent
import com.comulynx.wallet.android.ui.presentation.login.LoginFormState
import com.comulynx.wallet.android.ui.presentation.login.components.SignInFormSection
import com.comulynx.wallet.android.ui.theme.RoyalBlue

@Composable
fun PhoneLandScapeLayout(
    modifier: Modifier = Modifier,
    state: LoginFormState,
    onEvent: (LoginEvent) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(Dimens.mediumPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Icon(
                imageVector = Icons.Default.Fingerprint,
                contentDescription = null,
                modifier = Modifier.size(150.dp),
                tint = RoyalBlue
            )
        }
        item {
            Text(
                text = "LOGIN",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Black,
                    color = RoyalBlue
                )
            )
        }
        item {
            SignInFormSection(
                state = state,
                onEvent = onEvent
            )
        }
        item {
            SignInFormSection(
                state = state,
                onEvent = onEvent
            )

            Spacer(modifier = Modifier.height(100.dp))
        }
        item {
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
}