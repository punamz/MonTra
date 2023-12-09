package com.punam.montra.util

import androidx.annotation.DrawableRes
import com.punam.montra.R

sealed class OnboardContent(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String,
) {
    data object FirstPage : OnboardContent(
        image = R.drawable.onboard_1,
        title = "Gain total control\nof your money",
        description = "Become your own money manager\nand make every cent count"
    )

    data object SecondPage : OnboardContent(
        image = R.drawable.onboard_2,
        title = "Know where your \nmoney goes",
        description = "Track your transaction easily,\nwith categories and financial report "
    )

    data object ThirdPage : OnboardContent(
        image = R.drawable.onboard_3,
        title = "Planning ahead",
        description = "Setup your budget for each category\nso you in control"
    )
}
