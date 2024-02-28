package com.punam.montra.src.presentation.transaction.component

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
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.patrykandpatrick.vico.core.extension.setAll
import com.punam.montra.R
import com.punam.montra.util.AppConstant
import com.punam.montra.util.CategoryType
import com.punam.montra.util.OrderByType
import com.punam.montra.util.Routers

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(
    lastOrderByType: OrderByType,
    lastCategoryType: CategoryType?,
    lastCategories: List<String>,
    onConfirm: (CategoryType?, OrderByType, List<String>) -> Unit,
    onDismissRequest: () -> Unit,
    sheetState: SheetState,
    navController: NavController,
) {

    var categoryType by remember { mutableStateOf(lastCategoryType) }
    var orderByType by remember { mutableStateOf(lastOrderByType) }
    val categories = remember { mutableStateListOf<String>() }
    categories.addAll(lastCategories)

    fun resetData() {
        categoryType = null
        orderByType = OrderByType.Newest
        categories.clear()
    }

    fun updateCategoryType(newCategoryType: CategoryType) {
        categoryType = if (categoryType != newCategoryType) newCategoryType else null
    }

    if (navController.currentBackStackEntry?.savedStateHandle?.contains(AppConstant.SelectCategoryArgKey) == true) {
        val navBackData =
            navController.currentBackStackEntry?.savedStateHandle?.get<List<String>>(
                AppConstant.SelectCategoryArgKey
            ) ?: emptyList()
        categories.setAll(navBackData)
    }
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
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
                ElevatedButton(onClick = ::resetData) {
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
                    isSelected = categoryType == CategoryType.Income,
                    modifier = Modifier.weight(1f),
                    label = stringResource(id = R.string.income),
                    onClick = { updateCategoryType(CategoryType.Income) },
                    enable = categories.isEmpty()
                )
                SelectionButton(
                    isSelected = categoryType == CategoryType.Expenses,
                    modifier = Modifier.weight(1f),
                    label = stringResource(id = R.string.expenses),
                    onClick = { updateCategoryType(CategoryType.Expenses) },
                    enable = categories.isEmpty()
                )
                SelectionButton(
                    isSelected = categoryType == CategoryType.Transfer,
                    modifier = Modifier.weight(1f),
                    label = stringResource(R.string.transfer),
                    onClick = { updateCategoryType(CategoryType.Transfer) },
                    enable = categories.isEmpty()
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
                        isSelected = orderByType == OrderByType.Highest,
                        modifier = Modifier.weight(1f),
                        label = stringResource(R.string.highest),
                        onClick = { orderByType = OrderByType.Highest },
                    )
                    SelectionButton(
                        isSelected = orderByType == OrderByType.Lowest,
                        modifier = Modifier.weight(1f),
                        label = stringResource(R.string.lowest),
                        onClick = { orderByType = OrderByType.Lowest },
                    )
                    SelectionButton(
                        isSelected = orderByType == OrderByType.Newest,
                        modifier = Modifier.weight(1f),
                        label = stringResource(R.string.newest),
                        onClick = { orderByType = OrderByType.Newest },
                    )

                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SelectionButton(
                        isSelected = orderByType == OrderByType.Oldest,
                        modifier = Modifier.weight(1f),
                        label = stringResource(R.string.oldest),
                        onClick = { orderByType = OrderByType.Oldest },
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
                        val arg = Gson().toJson(categories.toList())
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
                        text = stringResource(R.string.selected, categories.size),
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
                    onConfirm.invoke(categoryType, orderByType, categories.toList())
                    onDismissRequest.invoke()
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(text = stringResource(R.string.apply))
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
