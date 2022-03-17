package com.unalzafer.moviescatalog.model.common

data class GenerateTokenRequest(
    val requestToken: String,
    val sessionId: String
)