package com.punam.montra.src.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.punam.montra.src.presentation.landing.LandingView
import com.punam.montra.src.presentation.login.LoginView
import com.punam.montra.src.presentation.onboard.OnboardView
import com.punam.montra.src.presentation.select_category.SelectCategoryView
import com.punam.montra.src.presentation.sign_up.SignUpView
import com.punam.montra.src.presentation.splash.SplashView
import com.punam.montra.src.presentation.transaction_detail.TransactionDetailView
import com.punam.montra.util.AppConstant
import com.punam.montra.util.Routers

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationGraph(
    navController: NavHostController,
    startDestination: String = Routers.Splash.name,
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
        composable(route = Routers.Landing.name) {
            LandingView(navController = navController)
        }
        composable(
            route = Routers.SelectCategory.name + "/{${AppConstant.SELECT_CATEGORY_ARG_KEY}}",
            arguments = listOf(
                navArgument(AppConstant.SELECT_CATEGORY_ARG_KEY) {
                    type = NavType.StringType
                    defaultValue = null
                    nullable = true
                }
            )
        ) {
            SelectCategoryView(navController = navController)
        }
        composable(
            route = Routers.TransactionDetail.name + "/{${AppConstant.TRANSACTION_DETAIL_ARG_KEY}}",
            arguments = listOf(
                navArgument(AppConstant.TRANSACTION_DETAIL_ARG_KEY) {
                    type = NavType.StringType
                    defaultValue = null
                    nullable = true
                }
            )
        ) {
            TransactionDetailView(navController = navController)
        }
    }
}