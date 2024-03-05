package com.punam.montra.src.presentation.filter_transaction.component

import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun SelectionButton(
    isSelected: Boolean,
    enable: Boolean = true,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier,
) {
    if (isSelected)
        FilledTonalButton(
            onClick = onClick,
            modifier = modifier,
            enabled = enable
        ) {
            Text(text = label)
        }
    else
        OutlinedButton(
            onClick = onClick,
            modifier = modifier,
            enabled = enable
        ) {
            Text(text = label)
        }

}
