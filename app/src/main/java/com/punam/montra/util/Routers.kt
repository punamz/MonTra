package com.punam.montra.util

sealed class Routers(val name: String) {
    data object Splash : Routers("splash")
    data object Onboard : Routers("onboard")
    data object Login : Routers("login")
    data object SignUp : Routers("sign_up")
    data object Landing : Routers("landing")
    data object Home : Routers("home")
    data object Transaction : Routers("transaction")
    data object Budget : Routers("budget")
    data object Profile : Routers("profile")
    data object FilterTransaction : Routers("filter_transaction")
    data object SelectCategory : Routers("select_category")
}
