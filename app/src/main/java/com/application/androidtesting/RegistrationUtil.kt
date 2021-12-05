package com.application.androidtesting

object RegistrationUtil {


    private val existingUsers = listOf("Aditya", "Cheke")

    /**
     * The input is not valid if....
    ... username/password is empty
    ... the username is already Taken
    ... the confirm pass is not the same as the real password
    ... the password contains less than 2 digits
     */

    fun validateRegistrationInput(
        userName: String,
        password: String,
        confirmPassword: String,
    ): Boolean {
        if (userName.isEmpty() || password.isEmpty())
            return false
        if (userName in existingUsers)
            return false
        if (password != confirmPassword)
            return false
        if (password.count { it.isDigit() } < 2)
            return false
        return true
    }
}
