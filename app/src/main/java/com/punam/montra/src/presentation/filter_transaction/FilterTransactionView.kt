package com.punam.montra.src.presentation.filter_transaction

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.gson.Gson
import com.punam.montra.R
import com.punam.montra.src.presentation.filter_transaction.component.SelectionButton
import com.punam.montra.util.AppConstant
import com.punam.montra.util.CategoryType
import com.punam.montra.util.OrderByType
import com.punam.montra.util.Routers


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterTransactionView(
    viewModel: FilterTransactionViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state.value
    val sheetState = rememberModalBottomSheetState(true)

    if (navController.currentBackStackEntry?.savedStateHandle?.contains(AppConstant.SelectCategoryArgKey) == true) {
        val navBackData = navController.currentBackStackEntry?.savedStateHandle?.get<List<String>>(
            AppConstant.SelectCategoryArgKey
        ) ?: emptyList()
        // clear data
        navController.currentBackStackEntry?.savedStateHandle?.remove<List<String>>(
            AppConstant.SelectCategoryArgKey
        )
        viewModel.onEvent(FilterTransactionEvent.UpdateCategories(navBackData))
    }
    ModalBottomSheet(
        onDismissRequest = {
            navController.popBackStack()
        },
        sheetState = sheetState
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 50.dp),

            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.filter_transaction),
                    style = MaterialTheme.typography.titleLarge,
                )
                ElevatedButton(onClick = { viewModel.onEvent(FilterTransactionEvent.Reset) }) {
                    Text(text = stringResource(R.string.reset))
                }
            }
            Text(
                text = stringResource(R.string.filter_by),
                style = MaterialTheme.typography.titleMedium,
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                SelectionButton(
                    isSelected = state.categoryType == CategoryType.Income,
                    modifier = Modifier.weight(1f),
                    label = stringResource(id = R.string.income),
                    onClick = {
                        viewModel.onEvent(
                            FilterTransactionEvent.ChangeCategoryType(
                                CategoryType.Income
                            )
                        )
                    },
                    enable = state.categories.isEmpty()
                )
                SelectionButton(
                    isSelected = state.categoryType == CategoryType.Expenses,
                    modifier = Modifier.weight(1f),
                    label = stringResource(id = R.string.expenses),
                    onClick = {
                        viewModel.onEvent(
                            FilterTransactionEvent.ChangeCategoryType(
                                CategoryType.Expenses
                            )
                        )
                    },
                    enable = state.categories.isEmpty()
                )
                SelectionButton(
                    isSelected = state.categoryType == CategoryType.Transfer,
                    modifier = Modifier.weight(1f),
                    label = stringResource(R.string.transfer),
                    onClick = {
                        viewModel.onEvent(
                            FilterTransactionEvent.ChangeCategoryType(
                                CategoryType.Transfer
                            )
                        )
                    },
                    enable = state.categories.isEmpty()
                )

            }
            Text(
                text = stringResource(R.string.sort_by),
                style = MaterialTheme.typography.titleMedium,
            )
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SelectionButton(
                        isSelected = state.orderByType == OrderByType.Highest,
                        modifier = Modifier.weight(1f),
                        label = stringResource(R.string.highest),
                        onClick = {
                            viewModel.onEvent(
                                FilterTransactionEvent.ChangeOrderByType(
                                    OrderByType.Highest
                                )
                            )
                        },
                    )
                    SelectionButton(
                        isSelected = state.orderByType == OrderByType.Lowest,
                        modifier = Modifier.weight(1f),
                        label = stringResource(R.string.lowest),
                        onClick = {
                            viewModel.onEvent(
                                FilterTransactionEvent.ChangeOrderByType(
                                    OrderByType.Lowest
                                )
                            )
                        },
                    )
                    SelectionButton(
                        isSelected = state.orderByType == OrderByType.Newest,
                        modifier = Modifier.weight(1f),
                        label = stringResource(R.string.newest),
                        onClick = {

                            viewModel.onEvent(
                                FilterTransactionEvent.ChangeOrderByType(
                                    OrderByType.Newest
                                )
                            )
                        },
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SelectionButton(
                        isSelected = state.orderByType == OrderByType.Oldest,
                        modifier = Modifier.weight(1f),
                        label = stringResource(R.string.oldest),
                        onClick = {
                            viewModel.onEvent(
                                FilterTransactionEvent.ChangeOrderByType(
                                    OrderByType.Oldest
                                )
                            )
                        },
                    )
                    Box(modifier = Modifier.weight(1f))
                    Box(modifier = Modifier.weight(1f))
                }
            }

            Text(
                text = stringResource(R.string.category),
                style = MaterialTheme.typography.titleMedium,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        val arg = Gson().toJson(state.categories.toList())
                        navController.navigate(Routers.SelectCategory.name + "/$arg")
                    }
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.choose_category),
                    style = MaterialTheme.typography.bodyMedium,
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.selected, state.categories.size),
                        style = MaterialTheme.typography.labelMedium,
                    )

                    Icon(
                        imageVector = Icons.Rounded.ChevronRight,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
            Button(
                onClick = {
                    val result = FilterTransactionResult(
                        categoryType = state.categoryType,
                        orderByType = state.orderByType,
                        categoriesSelected = state.categories
                    )
                    val a = Gson().toJson(result)
                    navController.previousBackStackEntry?.savedStateHandle?.set(
                        "Hi",
                        a
                    )
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(text = stringResource(R.string.apply))
            }
        }
    }
}

class FilterTransactionResult(
    val categoryType: CategoryType?,
    val orderByType: OrderByType,
    val categoriesSelected: List<String>,
)