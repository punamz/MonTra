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
