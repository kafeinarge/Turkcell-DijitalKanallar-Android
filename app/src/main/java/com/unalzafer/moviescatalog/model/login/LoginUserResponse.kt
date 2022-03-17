package com.unalzafer.moviescatalog.model.login

data class LoginUserResponse(
    val requestToken: String?,
    val responseCode: Int,
    val responseDesc: String,
    val sessionId: String?
)