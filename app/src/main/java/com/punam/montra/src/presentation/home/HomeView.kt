package com.punam.montra.src.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.punam.montra.R
import com.punam.montra.src.presentation.component.TransactionItem
import com.punam.montra.src.presentation.home.component.HomeAppBar
import com.punam.montra.src.presentation.home.component.TransactionChart

@Composable
fun HomeView(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController,
    changeToTransactionTab: () -> Unit,
    changeToProfileTab: () -> Unit,
) {

    val state = viewModel.state.value
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            HomeAppBar(viewModel, changeToProfileTab::invoke)
            LazyColumn(
                modifier = Modifier.fillMaxSize(),

                ) {
                item {
                    Text(
                        text = stringResource(R.string.spend_frequency),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(
                            start = 16.dp,
                            top = 13.dp,
                            end = 16.dp,
                            bottom = 5.dp
                        )
                    )
                    TransactionChart(
                        type = state.frequencyType,
                        datasetForModel = state.chartData,
                        isLoading = state.chartLoading
                    )
                    TabRow(
                        selectedTabIndex = viewModel.charTypeList.indexOf(state.frequencyType),
                        containerColor = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 14.dp)
                            .clip(RoundedCornerShape(50))
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.secondaryContainer,
                                CircleShape
                            )
                            .padding(1.dp),
                        indicator = { },
                        divider = { },
                    ) {
                        viewModel.charTypeList.forEachIndexed { index, text ->
                            val selected =
                                viewModel.charTypeList.indexOf(state.frequencyType) == index
                            Tab(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(50))
                                    .background(
                                        if (selected) MaterialTheme.colorScheme.primary
                                        else MaterialTheme.colorScheme.onPrimary
                                    ),
                                selected = selected,
                                onClick = {
                                    viewModel.onEvent((HomeEvent.ChangedFrequencyType(viewModel.charTypeList[index])))
                                },
                                text = {
                                    Text(
                                        text = text.toString(),
                                        color = if (selected) MaterialTheme.colorScheme.onPrimary
                                        else MaterialTheme.colorScheme.primary
                                    )
                                },
                                enabled = !state.chartLoading
                            )
                        }
                    }
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.recent_transaction),
                            style = MaterialTheme.typography.titleMedium,
                        )
                        ElevatedButton(onClick = changeToTransactionTab::invoke) {
                            Text(text = stringResource(R.string.see_all))
                        }
                    }
                }
                items(state.transactionHistory) { item ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .padding(bottom = 8.dp)
                    ) {
                        TransactionItem(
                            item,
                            navController
                        )
                    }
                }
            }
        }

    }
}
