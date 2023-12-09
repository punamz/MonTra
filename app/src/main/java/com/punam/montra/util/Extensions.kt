package com.punam.montra.util


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
