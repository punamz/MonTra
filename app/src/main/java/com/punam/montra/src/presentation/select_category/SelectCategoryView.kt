package com.punam.montra.src.presentation.select_category

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.punam.montra.R
import com.punam.montra.src.presentation.component.EmptyData
import com.punam.montra.src.presentation.component.LazyColumnLoadMore
import com.punam.montra.src.presentation.component.Loading
import com.punam.montra.src.presentation.select_category.component.CategoryItem
import com.punam.montra.util.AppConstant

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SelectCategoryView(
    viewModel: SelectCategoryViewModel = hiltViewModel(),
    navController: NavController,
    categoriesSelected: List<String>? = null
) {

    val state = viewModel.state.value
    val pullRefreshState = rememberPullRefreshState(
        refreshing = false,
        onRefresh = { viewModel.onEvent(SelectCategoryEvent.Refresh) }
    )
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(R.string.selected, state.categoriesSelected.size))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = ""
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {

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
                                        selected = state.categoriesSelected.contains(it.id),
                                        onChange = { value ->
                                            viewModel.onEvent(
                                                if (value) SelectCategoryEvent.SelectCategory(
                                                    it.id
                                                ) else SelectCategoryEvent.UnselectCategory(it.id)
                                            )
                                        }
                                    )
                                }
                            }
                            ElevatedButton(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = {
                                    navController.previousBackStackEntry?.savedStateHandle?.set(
                                        AppConstant.SelectCategoryArgKey,
                                        state.categoriesSelected
                                    )
                                    navController.popBackStack()
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
    }
}