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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.gson.Gson
import com.patrykandpatrick.vico.core.extension.setAll
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
                    text = "Filter Transaction",
                    style = MaterialTheme.typography.titleLarge,
                )
                ElevatedButton(onClick = ::resetData) {
                    Text(text = "Reset")
                }
            }
            Text(
                text = "Filter By",
                style = MaterialTheme.typography.titleMedium,
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                SelectionButton(
                    isSelected = categoryType == CategoryType.Income,
                    modifier = Modifier.weight(1f),
                    label = "Income",
                    onClick = { updateCategoryType(CategoryType.Income) },
                )
                SelectionButton(
                    isSelected = categoryType == CategoryType.Expenses,
                    modifier = Modifier.weight(1f),
                    label = "Expense",
                    onClick = { updateCategoryType(CategoryType.Expenses) },
                )
                SelectionButton(
                    isSelected = categoryType == CategoryType.Transfer,
                    modifier = Modifier.weight(1f),
                    label = "Transfer",
                    onClick = { updateCategoryType(CategoryType.Transfer) },
                )

            }
            Text(
                text = "Sort By",
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
                        label = "Highest",
                        onClick = { orderByType = OrderByType.Highest },
                    )
                    SelectionButton(
                        isSelected = orderByType == OrderByType.Lowest,
                        modifier = Modifier.weight(1f),
                        label = "Lowest",
                        onClick = { orderByType = OrderByType.Lowest },
                    )
                    SelectionButton(
                        isSelected = orderByType == OrderByType.Newest,
                        modifier = Modifier.weight(1f),
                        label = "Newest",
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
                        label = "Oldest",
                        onClick = { orderByType = OrderByType.Oldest },
                    )
                    Box(modifier = Modifier.weight(1f))
                    Box(modifier = Modifier.weight(1f))
                }
            }

            Text(
                text = "Category",
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
                    text = "Choose Category",
                    style = MaterialTheme.typography.bodyMedium,
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${categories.size} Selected",
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
                Text(text = "Apply")
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
