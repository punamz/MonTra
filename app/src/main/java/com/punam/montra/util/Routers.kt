package com.punam.montra.util

import kotlinx.serialization.Serializable

sealed class Routers {
    @Serializable
    data object Splash : Routers()

    @Serializable
    data object Onboard : Routers()

    @Serializable
    data object Login : Routers()

    @Serializable
    data object SignUp : Routers()

    @Serializable
    data object Landing : Routers()

    @Serializable
    data object Home : Routers()

    @Serializable
    data object Transaction : Routers()

    @Serializable
    data object Budget : Routers()

    @Serializable
    data object Profile : Routers()

}