package com.punam.montra.src.presentation.transaction

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.punam.montra.src.presentation.component.EmptyData
import com.punam.montra.src.presentation.component.LazyColumnLoadMore
import com.punam.montra.src.presentation.component.TransactionItem
import com.punam.montra.src.presentation.transaction.component.FilterBottomSheet
import com.punam.montra.src.presentation.transaction.component.FinancialReportButton
import com.punam.montra.src.presentation.transaction.component.TransactionAppBar

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TransactionView(
    viewModel: TransactionViewModel = hiltViewModel(),
    navController: NavController,
) {
    val state = viewModel.state.value
    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isLoading,
        onRefresh = { viewModel.onEvent(TransactionEvent.Refresh) }
    )
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            TransactionAppBar(onFilter = {
                viewModel.onEvent(TransactionEvent.ToggleFilterBottomSheet(true))
            })
            FinancialReportButton()

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .pullRefresh(pullRefreshState)
            ) {
                if (!state.isLoading && state.transactions.isEmpty())
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.size(200.dp)
                        ) {
                            EmptyData()
                        }

                    }
                else
                    LazyColumnLoadMore(
                        value = state.transactions,
                        isGettingMore = state.isGettingMore,
                        onClick = { viewModel.onEvent(TransactionEvent.GetMore) }) {
                        TransactionItem(item = it)
                    }
                PullRefreshIndicator(
                    refreshing = viewModel.state.value.isLoading,
                    state = pullRefreshState,
                    modifier = Modifier.align(Alignment.TopCenter),
                )
            }


            val sheetState = rememberModalBottomSheetState(true)
            if (state.showBottomSheet) {
                FilterBottomSheet(
                    onDismissRequest = {
                        viewModel.onEvent(
                            TransactionEvent.ToggleFilterBottomSheet(false)
                        )
                    },
                    sheetState = sheetState,
                    lastCategoryType = state.categoryType,
                    lastCategories = state.categories ?: emptyList(),
                    lastOrderByType = state.orderByType,
                    onConfirm = { categoryType, orderByType, categories ->
                        viewModel.onEvent(
                            TransactionEvent.Filter(
                                categoryType = categoryType,
                                orderByType = orderByType,
                                categories = categories
                            )
                        )
                    },
                    navController = navController,
                )

            }
        }
    }
}

@Composable
fun SelectionButton(
    isSelected: Boolean,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier,
) {
    if (isSelected)
        FilledTonalButton(onClick = onClick, modifier = modifier) {
            Text(text = label)
        }
    else
        OutlinedButton(onClick = onClick, modifier = modifier) {
            Text(text = label)
        }
}
