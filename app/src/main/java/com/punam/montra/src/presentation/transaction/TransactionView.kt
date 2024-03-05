package com.punam.montra.src.presentation.transaction

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.punam.montra.src.presentation.component.EmptyData
import com.punam.montra.src.presentation.component.LazyColumnLoadMore
import com.punam.montra.src.presentation.component.Loading
import com.punam.montra.src.presentation.component.TransactionItem
import com.punam.montra.src.presentation.filter_transaction.FilterTransactionResult
import com.punam.montra.src.presentation.transaction.component.FinancialReportButton
import com.punam.montra.src.presentation.transaction.component.TransactionAppBar
import com.punam.montra.util.Routers

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TransactionView(
    viewModel: TransactionViewModel = hiltViewModel(),
    navController: NavController,
    navBackStackEntry: NavBackStackEntry,
    filterTransactionResult: FilterTransactionResult?
) {
    val state = viewModel.state.value
    val pullRefreshState = rememberPullRefreshState(
        refreshing = false,
        onRefresh = { viewModel.onEvent(TransactionEvent.Refresh) }
    )
//
//    if (navBackStackEntry.savedStateHandle.contains("Hi")) {
//        val navBackData = navBackStackEntry.savedStateHandle.get<String>(
//            "Hi"
//        )
//
//        // clear data
//        navBackStackEntry.savedStateHandle.remove<String>(
//            "Hi"
//        )
//        val type = object : TypeToken<FilterTransactionResult>() {}.type
//        val a = Gson().fromJson<FilterTransactionResult>(navBackData, type)
//
//        if (navBackData != null)
//            viewModel.onEvent(
//                TransactionEvent.Filter(
//                    orderByType = a.orderByType,
//                    categoryType = a.categoryType,
//                    categories = a.categoriesSelected
//                )
//            )
//    }
    if (filterTransactionResult != null) {
        viewModel.onEvent(
            TransactionEvent.Filter(
                orderByType = filterTransactionResult.orderByType,
                categoryType = filterTransactionResult.categoryType,
                categories = filterTransactionResult.categoriesSelected
            )
        )
    }
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            TransactionAppBar(onFilter = {
                navController.navigate(Routers.FilterTransaction.name)
//                navController.navigate(Routers.SignUp.name)
            })
            FinancialReportButton()

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .pullRefresh(pullRefreshState)
            ) {
                if (state.isLoading)
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(150.dp)
                    ) {
                        Loading()
                    }
                else {
                    if (state.transactions.isEmpty())
                        LazyColumn(modifier = Modifier.fillMaxSize())
                        {
                            item {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .size(150.dp)
                                ) {
                                    EmptyData()
                                }
                            }
                        }
                    else
                        LazyColumnLoadMore(
                            value = state.transactions,
                            isGettingMore = state.isGettingMore,
                            onRefresh = { viewModel.onEvent(TransactionEvent.GetMore) }) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 20.dp)
                                    .padding(bottom = 8.dp)
                            ) {
                                TransactionItem(item = it)
                            }
                        }
                    PullRefreshIndicator(
                        refreshing = false,
                        state = pullRefreshState,
                        modifier = Modifier.align(Alignment.TopCenter),
                    )
                }

            }
//
//            if (state.showBottomSheet) {
//                FilterBottomSheet(
//                    onDismissRequest = {
//                        viewModel.onEvent(
//                            TransactionEvent.ToggleFilterBottomSheet(false)
//                        )
//                    },
//                    sheetState = sheetState,
//                    lastCategoryType = state.categoryType,
//                    lastCategories = state.categories ?: emptyList(),
//                    lastOrderByType = state.orderByType,
//                    onConfirm = { categoryType, orderByType, categories ->
//                        viewModel.onEvent(
//                            TransactionEvent.Filter(
//                                categoryType = categoryType,
//                                orderByType = orderByType,
//                                categories = categories
//                            )
//                        )
//                    },
//                    navController = navController,
//                )
//            }
        }
    }
}
