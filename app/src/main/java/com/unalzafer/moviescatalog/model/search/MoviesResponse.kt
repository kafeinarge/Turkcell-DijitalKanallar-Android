package com.unalzafer.moviescatalog.model.search

data class MoviesResponse(
    val movieList: List<Movie>,
    val responseCode: Int,
    val responseDesc: String
)