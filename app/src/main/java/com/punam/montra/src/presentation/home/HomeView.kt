package com.punam.montra.src.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun HomeView(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        androidx.compose.material3.Text(
            text = "Home",
            style = MaterialTheme.typography.displayMedium,
        )
    }

}


