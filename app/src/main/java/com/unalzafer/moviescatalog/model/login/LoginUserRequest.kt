package com.unalzafer.moviescatalog.model.login

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoginUserRequest(
    val userName: String,
    val password: String

)