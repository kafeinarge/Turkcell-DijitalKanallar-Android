package com.unalzafer.moviescatalog.model.moviedetail

data class MovieDetailResponse(
    val movieDetailType: MovieDetailType,
    val responseCode: Int,
    val responseDesc: String
)

