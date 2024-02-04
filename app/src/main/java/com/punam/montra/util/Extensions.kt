package com.punam.montra.util

import androidx.annotation.StringRes
import com.punam.montra.R

/**
 * validate input String is email format
 */
fun CharSequence.isEmail(): Boolean {
    val emailRegex = "^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}\$".toRegex()
    return this.matches(emailRegex)
}

/**
 * remove double whitespace in string
 */
fun CharSequence.beauty(): String {
    return this.replace("\n".toRegex(), "").replace("\\s+".toRegex(), " ")
}

/**
 * validate password rule
 */
fun CharSequence.isPassword(): Boolean {
    return this.length >= AppConstant.MIN_PASSWORD_LENGTH
}

@StringRes
fun CharSequence?.toStringRes(): Int {
    return when (this) {
        "err0001" -> R.string.err0001
        "err0002" -> R.string.err0002
        "err1001" -> R.string.err1001
        "err1002" -> R.string.err1002
        "err1003" -> R.string.err1003
        "err1004" -> R.string.err1004
        "err1005" -> R.string.err1005
        "err1006" -> R.string.err1006
        else -> R.string.err0000
    }
}

@StringRes
fun Int.toDayOfWeekStringRes(): Int {
    return when (this) {
        0 -> R.string.monday_short
        1 -> R.string.tuesday_short
        2 -> R.string.wednesday_short
        3 -> R.string.thursday_short
        4 -> R.string.february_short
        5 -> R.string.saturday_short
        6 -> R.string.sunday_short
        else -> R.string.monday_short
    }
}

fun Int.toMonthStringRes(): Int {
    return when (this) {
        0 -> R.string.january_short
        1 -> R.string.february_short
        2 -> R.string.march_short
        3 -> R.string.april_short
        4 -> R.string.may_short
        5 -> R.string.june_short
        6 -> R.string.july_short
        7 -> R.string.august_short
        8 -> R.string.september_short
        9 -> R.string.october_short
        10 -> R.string.november_short
        11 -> R.string.december_short
        else -> R.string.january_short
    }
}