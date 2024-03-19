package com.punam.montra.src.presentation.transaction_detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.DeleteForever
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.punam.montra.R
import com.punam.montra.src.domain.model.response.TransactionResponse
import com.punam.montra.util.CategoryType
import com.punam.montra.util.DateFormat
import com.punam.montra.util.format

@Composable
fun TransactionDetailAppbar(
    transaction: TransactionResponse,

    onDelete: () -> Unit,
    navController: NavController,

    ) {
    val color = when (transaction.category.type) {
        CategoryType.Expenses -> MaterialTheme.colorScheme.error
        CategoryType.Income -> MaterialTheme.colorScheme.primary
        CategoryType.Transfer -> MaterialTheme.colorScheme.secondary
    }
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 50.dp)
                .clip(RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp))
                .background(color = color)
                .padding(bottom = 50.dp)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    androidx.compose.material.Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.background
                    )
                }

                Text(
                    text = stringResource(R.string.detail_transaction),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.background
                )
                IconButton(onClick = onDelete) {
                    androidx.compose.material.Icon(
                        imageVector = Icons.Rounded.DeleteForever,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.background
                    )
                }

            }

            Text(
                text = "$${transaction.amount}",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier.padding(vertical = 15.dp)
            )
            Text(
                text = transaction.transactionAt.format(DateFormat.EEEEddMMMMyyyyhhmm),
                color = MaterialTheme.colorScheme.background
            )

        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(12.dp))
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    RoundedCornerShape(12.dp)
                )
                .background(color = MaterialTheme.colorScheme.background)
                .padding(
                    horizontal = 26.dp,
                    vertical = 12.dp
                )
        ) {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                ProValue(
                    title = "Type",
                    value = when (transaction.category.type) {
                        CategoryType.Expenses -> stringResource(id = R.string.expenses)
                        CategoryType.Income -> stringResource(id = R.string.income)
                        CategoryType.Transfer -> stringResource(id = R.string.transfer)
                    }
                )
                ProValue(title = "Category", value = transaction.category.category)
                ProValue(title = "Wallet", value = "Wallet")
            }
        }
    }

}

@Composable
fun ProValue(title: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(9.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}