package com.punam.montra.src.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.punam.montra.src.presentation.home.HomeView
import com.punam.montra.src.presentation.login.LoginView
import com.punam.montra.src.presentation.onboard.OnboardView
import com.punam.montra.src.presentation.sign_up.SignUpView
import com.punam.montra.src.presentation.splash.SplashView
import com.punam.montra.util.Routers

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationGraph(
    navController: NavHostController,
    startDestination: String = Routers.Splash.name
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Routers.Splash.name) {
            SplashView()
        }
        composable(route = Routers.Onboard.name) {
            OnboardView(navController = navController)
        }
        composable(route = Routers.Login.name) {
            LoginView(navController = navController)
        }
        composable(route = Routers.SignUp.name) {
            SignUpView(navController = navController)
        }
        composable(route = Routers.Home.name) {
            HomeView(navController = navController)
        }
    }
}