package com.punam.montra.src.presentation.select_category

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.punam.montra.R
import com.punam.montra.src.presentation.component.EmptyData
import com.punam.montra.src.presentation.component.LazyColumnLoadMore
import com.punam.montra.src.presentation.component.Loading
import com.punam.montra.src.presentation.select_category.component.CategoryItem

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectCategoryView(
    viewModel: SelectCategoryViewModel = hiltViewModel(),
    lastCategoriesSelected: List<String>,
    onConfirm: (List<String>) -> Unit,
    onDismissRequest: () -> Unit,
) {
    val categoriesSelected = remember { lastCategoriesSelected.toMutableStateList() }
    val state = viewModel.state.value
    val pullRefreshState = rememberPullRefreshState(
        refreshing = false,
        onRefresh = { viewModel.onEvent(SelectCategoryEvent.Refresh) }
    )
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
            if (state.categories.isEmpty())
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
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                    ) {
                        LazyColumnLoadMore(
                            value = state.categories,
                            isGettingMore = state.isGettingMore,
                            onRefresh = { viewModel.onEvent(SelectCategoryEvent.GetMore) }) {
                            CategoryItem(
                                item = it,
                                selected = categoriesSelected.contains(it.id),
                                onChange = { value ->
                                    if (value) {
                                        categoriesSelected.add(it.id)
                                    } else {
                                        categoriesSelected.remove(it.id)
                                    }
                                }
                            )
                        }
                    }
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        shape = RoundedCornerShape(16.dp),
                        onClick = {
                            onConfirm.invoke(categoriesSelected)
                            onDismissRequest.invoke()
                        }) {
                        Text(text = stringResource(R.string.apply))
                    }
                }

            PullRefreshIndicator(
                refreshing = false,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter),
            )
        }
    }
}