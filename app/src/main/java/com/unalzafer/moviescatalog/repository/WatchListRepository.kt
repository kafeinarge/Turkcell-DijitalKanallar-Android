package com.unalzafer.moviescatalog.repository

import com.unalzafer.moviescatalog.api.ApiService
import com.unalzafer.moviescatalog.model.common.GenerateToken
import com.unalzafer.moviescatalog.model.common.GenerateTokenRequest
import javax.inject.Inject

class WatchListRepository
@Inject
constructor(private val apiService: ApiService) {
        suspend fun getWatchList(generateTokenRequest: GenerateTokenRequest)=apiService.getAccountWatchListMovies(generateTokenRequest.requestToken,generateTokenRequest.sessionId)
}