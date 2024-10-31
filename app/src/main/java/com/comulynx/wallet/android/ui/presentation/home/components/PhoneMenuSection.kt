package com.comulynx.wallet.android.ui.presentation.home.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowWidthSizeClass
import com.comulynx.wallet.android.core.Dimens

@Composable
fun PhoneMenuSection(
    modifier: Modifier = Modifier,
    onBalanceCheck: () -> Unit,
    navigateToSendMoney: () -> Unit,
    navigateToViewStatement: () -> Unit,
    navigateToViewTransactions: () -> Unit,
    navigateToProfile: () -> Unit,
    logout: () -> Unit
) {
    val windowWidthSize = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass
    val configuration = LocalConfiguration.current
    val orientation = configuration.orientation

    when (windowWidthSize) {
        WindowWidthSizeClass.COMPACT -> {
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                LandScapeLayout(
                    modifier = modifier,
                    onBalanceCheck = onBalanceCheck,
                    navigateToSendMoney = navigateToSendMoney,
                    navigateToViewStatement = navigateToViewStatement,
                    navigateToViewTransactions = navigateToViewTransactions,
                    navigateToProfile = navigateToProfile,
                    logout = logout
                )
            } else {
                PortraitLayout(
                    modifier = modifier,
                    onBalanceCheck = onBalanceCheck,
                    navigateToSendMoney = navigateToSendMoney,
                    navigateToViewStatement = navigateToViewStatement,
                    navigateToViewTransactions = navigateToViewTransactions,
                    navigateToProfile = navigateToProfile,
                    logout = logout
                )
            }
        }

        WindowWidthSizeClass.MEDIUM -> {
            PortraitLayout(
                modifier = modifier,
                onBalanceCheck = onBalanceCheck,
                navigateToSendMoney = navigateToSendMoney,
                navigateToViewStatement = navigateToViewStatement,
                navigateToViewTransactions = navigateToViewTransactions,
                navigateToProfile = navigateToProfile,
                logout = logout
            )
        }

        WindowWidthSizeClass.EXPANDED -> {
            LandScapeLayout(
                modifier = modifier,
                onBalanceCheck = onBalanceCheck,
                navigateToSendMoney = navigateToSendMoney,
                navigateToViewStatement = navigateToViewStatement,
                navigateToViewTransactions = navigateToViewTransactions,
                navigateToProfile = navigateToProfile,
                logout = logout
            )
        }
    }
}


@Composable
private fun LandScapeLayout(
    modifier: Modifier = Modifier,
    onBalanceCheck: () -> Unit,
    navigateToSendMoney: () -> Unit,
    navigateToViewStatement: () -> Unit,
    navigateToViewTransactions: () -> Unit,
    navigateToProfile: () -> Unit,
    logout: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(Dimens.mediumPadding)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            HomeMenuItem(
                modifier = Modifier.weight(0.5f),
                imageVector = Icons.Default.AccountBalance,
                menuName = "Balance",
                onClick = onBalanceCheck
            )
            Spacer(modifier = Modifier.width(Dimens.mediumPadding))
            HomeMenuItem(
                modifier = Modifier.weight(0.5f),
                imageVector = Icons.AutoMirrored.Filled.Send,
                menuName = "Send Money",
                onClick = navigateToSendMoney
            )
            Spacer(modifier = Modifier.width(Dimens.mediumPadding))
            HomeMenuItem(
                modifier = Modifier.weight(0.5f),
                imageVector = Icons.Default.Description,
                menuName = "View Statement",
                onClick = navigateToViewStatement
            )
        }
        Spacer(modifier = Modifier.height(Dimens.largePadding))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            HomeMenuItem(
                modifier = Modifier.weight(0.5f),
                imageVector = Icons.Default.History,
                menuName = "View Transactions",
                onClick = navigateToViewTransactions
            )
            Spacer(modifier = Modifier.width(Dimens.mediumPadding))
            HomeMenuItem(
                modifier = Modifier.weight(0.5f),
                imageVector = Icons.Default.PermIdentity,
                menuName = "Profile",
                onClick = navigateToProfile
            )
            Spacer(modifier = Modifier.width(Dimens.mediumPadding))
            HomeMenuItem(
                modifier = Modifier.weight(0.5f),
                imageVector = Icons.AutoMirrored.Filled.Logout,
                menuName = "Logout",
                iconTint = MaterialTheme.colorScheme.error,
                onClick = logout
            )
        }

    }
}





@Composable
fun PortraitLayout(
    modifier: Modifier = Modifier,
    onBalanceCheck: () -> Unit,
    navigateToSendMoney: () -> Unit,
    navigateToViewStatement: () -> Unit,
    navigateToViewTransactions: () -> Unit,
    navigateToProfile: () -> Unit,
    logout: () -> Unit
) {
    Column(modifier = modifier
        .fillMaxSize()
        .padding(Dimens.mediumPadding)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            HomeMenuItem(
                modifier = Modifier.weight(0.5f),
                imageVector = Icons.Default.AccountBalance,
                menuName = "Balance",
                onClick = onBalanceCheck
            )
            Spacer(modifier = Modifier.width(Dimens.mediumPadding))
            HomeMenuItem(
                modifier = Modifier.weight(0.5f),
                imageVector = Icons.AutoMirrored.Filled.Send,
                menuName = "Send Money",
                onClick = navigateToSendMoney
            )
        }
        Spacer(modifier = Modifier.height(Dimens.mediumPadding))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            HomeMenuItem(
                modifier = Modifier.weight(0.5f),
                imageVector = Icons.Default.Description,
                menuName = "View Statement",
                onClick = navigateToViewStatement
            )
            Spacer(modifier = Modifier.width(Dimens.mediumPadding))
            HomeMenuItem(
                modifier = Modifier.weight(0.5f),
                imageVector = Icons.Default.History,
                menuName = "View Transactions",
                onClick = navigateToViewTransactions
            )
        }
        Spacer(modifier = Modifier.height(Dimens.mediumPadding))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            HomeMenuItem(
                modifier = Modifier.weight(0.5f),
                imageVector = Icons.Default.PermIdentity,
                menuName = "Profile",
                onClick = navigateToProfile
            )
            Spacer(modifier = Modifier.width(Dimens.mediumPadding))
            HomeMenuItem(
                modifier = Modifier.weight(0.5f),
                imageVector = Icons.AutoMirrored.Filled.Logout,
                menuName = "Logout",
                iconTint = MaterialTheme.colorScheme.error,
                onClick = logout
            )
        }
        Spacer(modifier = Modifier.height(Dimens.mediumPadding))
    }


}


@Composable
fun HomeMenuItem(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    menuName: String,
    iconTint: Color = MaterialTheme.colorScheme.onSurface,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(Dimens.buttonSize),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = imageVector, contentDescription = "$menuName button", tint = iconTint
            )
            Spacer(modifier = Modifier.width(Dimens.smallPadding))
            Text(
                text = menuName,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

        }

    }
}
