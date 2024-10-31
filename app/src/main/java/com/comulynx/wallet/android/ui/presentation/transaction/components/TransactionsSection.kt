package com.comulynx.wallet.android.ui.presentation.transaction.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.comulynx.wallet.android.core.Dimens
import com.comulynx.wallet.android.data.model.response.Transaction
import com.comulynx.wallet.android.ui.theme.Black
import com.comulynx.wallet.android.ui.theme.Card1
import com.comulynx.wallet.android.ui.theme.Card2
import com.comulynx.wallet.android.ui.theme.Card3
import com.comulynx.wallet.android.ui.theme.Card4
import com.comulynx.wallet.android.ui.theme.Card5

@Composable
fun TransactionsSection(
    modifier: Modifier = Modifier, transactions: List<Transaction>
) {

    val totalAmount = transactions.sumOf { it.amount }.toString()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(Dimens.mediumPadding)
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(Dimens.smallPadding)
        ) {
            itemsIndexed(transactions) { index, item ->
                val colorList: List<Color> = listOf(Card1, Card2, Card3, Card4, Card5)
                val color = colorList[index % colorList.size]
                TransactionItem(
                    transaction = item, color = color
                )
            }
        }
        Spacer(modifier = Modifier.height(Dimens.mediumPadding))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(Dimens.smallPadding)),
            border = BorderStroke(
                1.dp, MaterialTheme.colorScheme.surface.copy(alpha = 0.65f)
            ),
            colors = CardDefaults.cardColors(
                contentColor = MaterialTheme.colorScheme.onSurface,
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(Dimens.mediumPadding)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Total",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Black),
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = totalAmount,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Black),
                    color = MaterialTheme.colorScheme.secondary
                )

            }

        }
        Spacer(modifier = Modifier.height(Dimens.extraLargePadding))
    }
}


@Composable
fun TransactionItem(
    modifier: Modifier = Modifier, transaction: Transaction, color: Color
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(Dimens.smallPadding)),
        border = BorderStroke(
            1.dp, MaterialTheme.colorScheme.surface.copy(alpha = 0.65f)
        ),
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.onSurface,
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.mediumPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(color)
                        .padding(all = Dimens.smallPadding)
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountBalanceWallet,
                        contentDescription = "payments icon",
                        modifier = Modifier
                            .size(Dimens.largePadding)
                            .align(Alignment.Center),
                        tint = Black
                    )
                }
                Spacer(modifier = Modifier.width(Dimens.smallPadding))
                Column {
                    Text(
                        text = "Transaction ID",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = modifier.height(Dimens.extraSmallPadding))
                    Text(
                        text = transaction.transactionId,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "Amount",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = modifier.height(Dimens.extraSmallPadding))
                Text(
                    text = transaction.amount.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.secondary
                )

            }
        }
    }

}