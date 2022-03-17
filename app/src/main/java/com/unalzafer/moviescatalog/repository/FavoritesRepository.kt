package com.unalzafer.moviescatalog.repository

import com.unalzafer.moviescatalog.api.ApiService
import com.unalzafer.moviescatalog.model.common.GenerateTokenRequest
import javax.inject.Inject

class FavoritesRepository
@Inject
constructor(private val apiService: ApiService) {
    suspend fun getFavorites(generateTokenRequest: GenerateTokenRequest) =
        apiService.getAccountFavoriteMovies(
            generateTokenRequest.requestToken, generateTokenRequest.sessionId
        )
}