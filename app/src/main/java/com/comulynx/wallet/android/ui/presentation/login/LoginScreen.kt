package com.comulynx.wallet.android.ui.presentation.login

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowWidthSizeClass
import com.comulynx.wallet.android.core.Dimens
import com.comulynx.wallet.android.ui.presentation.components.BasePreview
import com.comulynx.wallet.android.ui.presentation.login.layouts.LoginTabletLandScapeLayout
import com.comulynx.wallet.android.ui.presentation.login.layouts.PhoneLandScapeLayout
import com.comulynx.wallet.android.ui.presentation.login.layouts.PhoneLoginPortraitLayout
import com.comulynx.wallet.android.ui.presentation.login.layouts.TabletLoginPortraitLayout
import com.comulynx.wallet.android.ui.theme.MobileWalletTheme

@Composable
fun LoginScreen(
    state: LoginFormState,
    onEvent: (LoginEvent) -> Unit
) {
    Scaffold { innerPadding ->
        LoginScreenContent(
            modifier = Modifier.padding(innerPadding),
            state = state,
            onEvent = onEvent
        )
    }
}

@Composable
fun LoginScreenContent(
    modifier: Modifier = Modifier,
    state: LoginFormState,
    onEvent: (LoginEvent) -> Unit
) {
    // Get the adaptive info and window size class

    val windowWidthSize = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass
    val configuration = LocalConfiguration.current
    val orientation = configuration.orientation

    when (windowWidthSize) {
        WindowWidthSizeClass.COMPACT -> {
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                PhoneLandScapeLayout(
                    modifier = modifier,
                    onEvent = onEvent,
                    state = state
                )
            } else {
                PhoneLoginPortraitLayout(
                    modifier = modifier,
                    onEvent = onEvent,
                    state = state
                )
            }

        }

        WindowWidthSizeClass.MEDIUM -> {
            TabletLoginPortraitLayout(
                modifier = modifier,
                onEvent = onEvent,
                state = state,
                windowWidthSize = windowWidthSize
            )

        }

        WindowWidthSizeClass.EXPANDED -> {
            LoginTabletLandScapeLayout(
                modifier = modifier,
                onEvent = onEvent,
                state = state,
                windowWidthSize = windowWidthSize
            )

        }
    }
}


@BasePreview
@Composable
private fun LoginScreenPreview() {
    MobileWalletTheme {
        Surface {
            LoginScreen(
                state = LoginFormState(),
                onEvent = {}
            )
        }
    }
}