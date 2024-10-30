package com.comulynx.wallet.android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.comulynx.wallet.android.ui.presentation.login.LoginScreen
import com.comulynx.wallet.android.ui.presentation.login.LoginViewModel
import com.comulynx.wallet.android.ui.theme.MobileWalletTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val TAG = "MainActivity"

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            MobileWalletTheme {
                LaunchedEffect(key1 = viewModel.loginUIState) {
                    viewModel.loginUIState.collect { state ->
                        if (state.errorMassage != null) {
                            Log.e(TAG, "onCreate: error::${state.errorMassage}")
                        }
                        if (state.success.isNotEmpty()) {
                            Log.d(TAG, "onCreate: ${state.success}")
                        }
                    }
                }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(
                        modifier = Modifier.padding(innerPadding),
                        state = viewModel.state,
                        onEvent = viewModel::onEvents
                    )
                }
            }
        }
    }
}

