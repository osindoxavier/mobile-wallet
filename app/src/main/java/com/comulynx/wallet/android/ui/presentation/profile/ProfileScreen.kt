package com.comulynx.wallet.android.ui.presentation.profile

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.comulynx.wallet.android.R
import com.comulynx.wallet.android.core.Dimens
import com.comulynx.wallet.android.data.model.entity.CustomerEntity
import com.comulynx.wallet.android.ui.presentation.components.BaseTopBar
import com.comulynx.wallet.android.ui.presentation.components.LoadingDialog
import com.comulynx.wallet.android.ui.presentation.home.UiStateFlow

@Composable
fun ProfileScreen(
    uiState: UiStateFlow<CustomerEntity>,
    onNavigationBack: () -> Boolean
) {
    val tag = "ProfileScreen"
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            BaseTopBar(navOnNavigation = {
                onNavigationBack()
            }, title = "Profile")
        }
    ) { innerPadding ->
        when (uiState) {
            is UiStateFlow.Error -> {
                Log.e(tag, "ProfileScreen::${uiState.errorMessage}")
            }

            is UiStateFlow.Loading -> {
                LoadingDialog()
            }

            is UiStateFlow.Success -> {
                ProfileContent(
                    modifier = Modifier
                        .padding(innerPadding),
                    profile = uiState.data
                )
            }
        }
    }

}

@Composable
fun ProfileContent(modifier: Modifier = Modifier, profile: CustomerEntity) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .border(2.dp, MaterialTheme.colorScheme.secondary, CircleShape)
                .padding(Dimens.mediumPadding)
        ) {
            // Load image with placeholder
            Image(
                painter = painterResource(id = R.drawable.man),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
            )
        }
        Spacer(modifier = Modifier.height(Dimens.mediumPadding))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${profile.firstName} ${profile.lastName}",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Black),
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = profile.email,
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onBackground.copy(0.75f)
            )
            Text(
                text = "Customer ID ${profile.customerId}",
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onBackground.copy(0.75f)
            )
            Text(
                text = "Account Number ${profile.customerId}",
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onBackground.copy(0.75f)
            )
        }
    }
}
