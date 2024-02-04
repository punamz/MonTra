package com.punam.montra.src.presentation.landing

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CurrencyExchange
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.PieChart
import androidx.compose.ui.graphics.vector.ImageVector
import com.punam.montra.R
import com.punam.montra.util.Routers

sealed class LandingBottomItem(
    val route: Routers,
    @StringRes val label: Int,
    val icon: ImageVector,
) {
    data object Home : LandingBottomItem(
        route = Routers.Home,
        label = R.string.home,
        icon = Icons.Rounded.Home
    )

    data object Transaction : LandingBottomItem(
        route = Routers.Transaction,
        label = R.string.transaction,
        icon = Icons.Rounded.CurrencyExchange
    )

    data object Budget : LandingBottomItem(
        route = Routers.Budget,
        label = R.string.budget,
        icon = Icons.Rounded.PieChart
    )

    data object Profile : LandingBottomItem(
        route = Routers.Profile,
        label = R.string.profile,
        icon = Icons.Rounded.Person
    )

}