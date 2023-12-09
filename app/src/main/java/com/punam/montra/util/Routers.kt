package com.punam.montra.util

sealed class Routers(val name: String) {
    data object Splash : Routers("splash")
    data object Onboard : Routers("onboard")
    data object Login : Routers("login")
    data object SignUp : Routers("sign_up")
    data object Home : Routers("home")
}
