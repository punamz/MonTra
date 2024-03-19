package com.punam.montra.src.presentation.transaction_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.punam.montra.src.presentation.transaction_detail.component.ConfirmDeleteBottomSheet
import com.punam.montra.src.presentation.transaction_detail.component.TransactionDetailAppbar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionDetailView(
    viewModel: TransactionDetailViewModel = hiltViewModel(),
    navController: NavController,
) {
    val state = viewModel.state.value
    val sheetState = rememberModalBottomSheetState(true)

    Column {
        state.transaction?.let {
            TransactionDetailAppbar(
                transaction = it,
                navController = navController,
                onDelete = {
                    viewModel.onEvent(
                        TransactionDetailEvent.ToggleFilterBottomSheet(true)
                    )
                }
            )
        }

        LazyColumn(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            item {
                state.transaction?.let {
                    Text(
                        text = "Description",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(bottom = 15.dp)
                    )
                    Text(
                        text = it.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }

        }
        if (state.showDeleteBottomSheet) {
            ConfirmDeleteBottomSheet(
                onDismissRequest = {
                    viewModel.onEvent(
                        TransactionDetailEvent.ToggleFilterBottomSheet(false)
                    )
                },
                sheetState = sheetState,
                onConfirm = {
                    viewModel.onEvent(
                        TransactionDetailEvent.ToggleFilterBottomSheet(false)
                    )
                },
            )
        }
    }

}