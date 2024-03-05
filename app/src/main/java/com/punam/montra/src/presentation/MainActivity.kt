package com.punam.montra.src.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.punam.montra.src.presentation.splash.SplashViewModel
import com.punam.montra.ui.theme.MonTraTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    @Inject
    lateinit var splashViewModel: SplashViewModel


    @OptIn(ExperimentalMaterialNavigationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setKeepOnScreenCondition {
            splashViewModel.isLoading.value
        }

        setContent {
            MonTraTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val bottomSheetNavigator = rememberBottomSheetNavigator()
                    val navController = rememberNavController(bottomSheetNavigator)
                    NavigationGraph(
                        bottomSheetNavigator = bottomSheetNavigator,
                        navController = navController,
                        startDestination = splashViewModel.startDestination.value
                    )

                }
            }
        }
    }
}