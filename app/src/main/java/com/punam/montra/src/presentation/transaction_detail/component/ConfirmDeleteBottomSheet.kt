package com.punam.montra.src.presentation.transaction_detail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.punam.montra.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmDeleteBottomSheet(
    onConfirm: () -> Unit,
    onDismissRequest: () -> Unit,
    sheetState: SheetState,
) {

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 50.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(R.string.remove_transaction_title),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )

            Text(
                text = stringResource(R.string.emove_transaction_description),
                textAlign = TextAlign.Center
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                FilledTonalButton(
                    onClick = {
                        onConfirm.invoke()
                        onDismissRequest.invoke()
                    },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(text = stringResource(R.string.no))
                }
                Button(
                    onClick = {
                        onConfirm.invoke()
                        onDismissRequest.invoke()
                    },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(text = stringResource(R.string.yes))
                }
            }


        }
    }
}

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
