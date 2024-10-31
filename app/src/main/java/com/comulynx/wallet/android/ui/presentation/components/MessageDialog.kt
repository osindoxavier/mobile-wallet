package com.comulynx.wallet.android.ui.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import com.comulynx.wallet.android.R
import com.comulynx.wallet.android.core.Dimens
import com.comulynx.wallet.android.data.model.response.AccountBalanceApiResponse
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@SuppressLint("NewApi")
@Composable
fun MessageDialog(
    setShowDialog: (Boolean) -> Unit,
    response: AccountBalanceApiResponse
) {
    val currentTime = rememberUpdatedState(LocalDateTime.now())


    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(Dimens.mediumPadding),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(Dimens.mediumPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Account NO: ${response.accountNo}",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Black),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(Dimens.extraSmallPadding))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(Dimens.extraSmallPadding))
                Text(
                    text = "Your account balance is ${response.balance} as at ${
                        currentTime.value.format(
                            DateTimeFormatter.ofPattern("HH:mm:ss")
                        )
                    }",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(Dimens.mediumPadding))
                Button(
                    onClick = {
                        setShowDialog(true)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(Dimens.extraLargePadding),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onSecondary
                    ),
                    shape = RoundedCornerShape(Dimens.smallPadding)
                ) {
                    Text(
                        text = "Ok",
                        color = Color.White,
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
                        textAlign = TextAlign.Center,
                    )
                }
                Spacer(modifier = Modifier.height(Dimens.mediumPadding))
            }
        }
    }

}