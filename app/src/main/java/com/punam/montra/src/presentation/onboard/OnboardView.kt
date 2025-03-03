package com.punam.montra.src.presentation.onboard

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.punam.montra.R
import com.punam.montra.src.presentation.onboard.components.OnboardBody
import com.punam.montra.util.Routers

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalAnimationApi
@Composable
fun OnboardView(
    navController: NavController,
    viewModel: OnboardViewModel = hiltViewModel(),
) {
    val pagerState: PagerState = rememberPagerState(pageCount = { viewModel.page.size })
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .wrapContentHeight()
                .weight(1f),
        ) { OnboardBody(content = viewModel.page[it]) }
        HorizontalPagerIndicator(
            pageCount = 3,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 40.dp, bottom = 25.dp),
            pagerState = pagerState,
            activeColor = MaterialTheme.colorScheme.primary,
            inactiveColor = MaterialTheme.colorScheme.primaryContainer,
        )
        Button(
            onClick = {
                viewModel.saveOnBoardingState(completed = true)
                navController.navigate(Routers.SignUp) {
                    popUpTo(navController.graph.id) { inclusive = true }
                }
            },
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth(),
        ) { Text(text = stringResource(R.string.sign_up)) }
        ElevatedButton(
            onClick = {
                viewModel.saveOnBoardingState(completed = true)
                navController.navigate(Routers.Login) {
                    popUpTo(navController.graph.id) { inclusive = true }
                }
            },
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 8.dp, bottom = 16.dp)
                .fillMaxWidth(),
        ) { Text(text = stringResource(R.string.login)) }
    }
}
