package com.comulynx.wallet.android.ui.presentation.login.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.window.core.layout.WindowWidthSizeClass
import com.comulynx.wallet.android.core.Dimens
import com.comulynx.wallet.android.ui.presentation.login.LoginEvent
import com.comulynx.wallet.android.ui.presentation.login.LoginFormState
import com.comulynx.wallet.android.ui.presentation.login.components.SignInFormSection
import com.comulynx.wallet.android.ui.theme.RoyalBlue

@Composable
fun LoginTabletLandScapeLayout(
    modifier: Modifier = Modifier,
    onEvent: (LoginEvent) -> Unit,
    state: LoginFormState,
    windowWidthSize: WindowWidthSizeClass
) {

    val layoutWeight = when (windowWidthSize) {
        WindowWidthSizeClass.COMPACT -> 1f
        WindowWidthSizeClass.MEDIUM -> .85f
        WindowWidthSizeClass.EXPANDED -> .55f
        else -> 1f
    }

    Row(
        modifier = modifier
            .padding(Dimens.extraLargePadding)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(.7f)
        ) {
            Icon(
                imageVector = Icons.Default.Fingerprint,
                contentDescription = null,
                modifier = Modifier
                    .size(250.dp)
                    .align(Alignment.Center),
                tint = RoyalBlue
            )
        }

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "LOGIN",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Black,
                    color = RoyalBlue
                )
            )
            SignInFormSection(
                modifier = modifier.fillMaxWidth(layoutWeight),
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
                    .fillMaxWidth(layoutWeight)
            ) {
                Text(
                    text = "Login",
                    style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold)
                )
            }
        }

    }
}