package com.unalzafer.moviescatalog.repository

import com.unalzafer.moviescatalog.api.ApiService
import javax.inject.Inject

class MoviesRepository
@Inject
constructor(private val apiService: ApiService) {
        suspend fun getSearch(text:String)=apiService.getSearch(text)
}