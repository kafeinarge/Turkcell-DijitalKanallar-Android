package com.unalzafer.moviescatalog.repository

import com.unalzafer.moviescatalog.api.ApiService
import com.unalzafer.moviescatalog.model.common.GenerateTokenRequest
import javax.inject.Inject

class MovieDetailRepository
@Inject
constructor(private val apiService: ApiService) {
        suspend fun getMovieDetail(id:String)=apiService.getMovieDetail(id)

        suspend fun addRemoveAndFavorite(favorite:Boolean,mediaId:String,request: GenerateTokenRequest)=apiService.addRemoveAndFavorite(favorite,mediaId,request.requestToken,request.sessionId)
}