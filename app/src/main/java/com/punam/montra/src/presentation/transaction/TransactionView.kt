package com.punam.montra.src.presentation.transaction

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.punam.montra.src.presentation.component.LazyColumnLoadMore
import com.punam.montra.src.presentation.component.TransactionItem
import com.punam.montra.src.presentation.transaction.component.FinancialReportButton
import com.punam.montra.src.presentation.transaction.component.TransactionAppBar

@OptIn(ExperimentalMaterialApi::class)
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
            TransactionAppBar()
            FinancialReportButton()
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .pullRefresh(pullRefreshState)
            ) {
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
        }
    }
}

