package com.example.ui_library.utils




fun validateEmail(email: String): Boolean {
    val regex = Regex("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+\$")
    return  email.matches(regex)
}

fun validatePassword(password: String): Pair<Boolean, String> {
    if (password.length < 8) {
        return Pair(false, "Password must be at least 8 characters long")
    }
    if (!Regex("[A-Z]").containsMatchIn(password)) {
        return Pair(false, "Password must contain at least one lowercase letter")
    }
    if(!Regex("[a-z]").containsMatchIn(password)) {
        return Pair(false, "Password must contain at least one uppercase letter")
    }
    if(!Regex("\\d").containsMatchIn(password)) {
        return Pair(false, "digit")
    }

    if (!Regex("[!@#\$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]").containsMatchIn(password)) {
        return Pair(false, "special_character")
    }

    return Pair(true, "")

}