package com.punam.montra.src.presentation.home.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TransactionItem(item: String) {
    Text(
        text = item,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(vertical = 10.dp)
    )
}
