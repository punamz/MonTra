package com.punam.montra.src.presentation

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.punam.montra.src.presentation.filter_transaction.FilterTransactionResult
import com.punam.montra.src.presentation.filter_transaction.FilterTransactionView
import com.punam.montra.src.presentation.landing.LandingView
import com.punam.montra.src.presentation.login.LoginView
import com.punam.montra.src.presentation.onboard.OnboardView
import com.punam.montra.src.presentation.select_category.SelectCategoryView
import com.punam.montra.src.presentation.sign_up.SignUpView
import com.punam.montra.src.presentation.splash.SplashView
import com.punam.montra.util.AppConstant
import com.punam.montra.util.Routers

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class)
@Composable
fun NavigationGraph(
    bottomSheetNavigator: BottomSheetNavigator,
    navController: NavHostController,
    startDestination: String = Routers.Splash.name,
) {
    ModalBottomSheetLayout(bottomSheetNavigator = bottomSheetNavigator) {
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
                val navBackData = it.savedStateHandle.get<String>("Hi")
                Log.d("TAG", "NavigationGraph: $navBackData")
                // clear data
                it.savedStateHandle.remove<String>("Hi")
                val type = object : TypeToken<FilterTransactionResult?>() {}.type
                val a = Gson().fromJson<FilterTransactionResult?>(navBackData, type)
                Log.d("TAG", "NavigationGraph: 1$a")
                LandingView(navController = navController, it, a)
            }
            bottomSheet(route = Routers.FilterTransaction.name) {
                FilterTransactionView(navController = navController)
            }
            bottomSheet(
                route = Routers.SelectCategory.name + "/{${AppConstant.SelectCategoryArgKey}}",
                arguments = listOf(
                    navArgument(AppConstant.SelectCategoryArgKey) {
                        type = NavType.StringType
                        defaultValue = null
                        nullable = true
                    }
                )
            ) {
                SelectCategoryView(navController = navController)
            }
        }
    }
}