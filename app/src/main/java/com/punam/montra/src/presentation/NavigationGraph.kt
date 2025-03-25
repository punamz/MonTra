package com.punam.montra.src.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.punam.montra.src.presentation.landing.LandingView
import com.punam.montra.src.presentation.login.LoginView
import com.punam.montra.src.presentation.onboard.OnboardView
import com.punam.montra.src.presentation.sign_up.SignUpView
import com.punam.montra.src.presentation.splash.SplashView
import com.punam.montra.util.Routers

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationGraph(
    navController: NavHostController,
    startDestination: Any = Routers.Splash,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<Routers.Splash> {
            SplashView()
        }
        composable<Routers.Onboard> {
            OnboardView(navController = navController)
        }
        composable<Routers.Login> {
            LoginView(navController = navController)
        }
        composable<Routers.SignUp> {
            SignUpView(navController = navController)
        }
        composable<Routers.Landing> {
            LandingView(navController = navController)
        }
    }
}

