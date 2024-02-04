package com.punam.montra.src.presentation.landing

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.punam.montra.src.presentation.budget.BudgetView
import com.punam.montra.src.presentation.home.HomeView
import com.punam.montra.src.presentation.profile.ProfileView
import com.punam.montra.src.presentation.transaction.TransactionView
import com.punam.montra.util.Routers

@Composable
fun LandingView(
    navController: NavController,
) {
    val bottomNavController = rememberNavController()
    val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val screens = listOf(

        LandingBottomItem.Home,
        LandingBottomItem.Transaction,
        LandingBottomItem.Budget, LandingBottomItem.Profile,


        )
    Scaffold(
        bottomBar = {
            NavigationBar {

                screens.forEach { screen ->
                    NavigationBarItem(

                        selected = currentDestination?.hierarchy?.any { it.route == screen.route.name } == true,
                        label = {
                            Text(stringResource(id = screen.label))
                        },
                        icon = {
                            Icon(
                                screen.icon,
                                contentDescription = stringResource(id = screen.label)
                            )
                        },
                        onClick = {
                            bottomNavController.navigate(screen.route.name) {
                                popUpTo(bottomNavController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            bottomNavController,
            startDestination = Routers.Home.name,
            Modifier.padding(paddingValues)
        ) {
            composable(Routers.Home.name) { HomeView(navController = navController) }
            composable(Routers.Transaction.name) { TransactionView(navController = navController) }
            composable(Routers.Budget.name) { BudgetView(navController = navController) }
            composable(Routers.Profile.name) { ProfileView(navController = navController) }
        }
    }
}